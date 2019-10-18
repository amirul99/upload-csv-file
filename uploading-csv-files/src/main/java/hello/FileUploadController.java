package hello;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import hello.services.CsvFileReader;
import hello.storage.StorageFileNotFoundException;
import hello.storage.StorageProperties;
import hello.storage.StorageService;

@Controller
public class FileUploadController {

    private final StorageService storageService;
    
    @Autowired
    public CsvFileReader csvFileReader;
    
    
    @Autowired
    public StorageProperties storegproperties;

    @Autowired
    public FileUploadController(StorageService storageService, 
    		StorageProperties storegproperties, CsvFileReader csvFileReader) {
        this.storageService = storageService;
        this.storegproperties = storegproperties;
        this.csvFileReader = csvFileReader;
       
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));
        //
        
        System.out.println();

        return "uploadForm";
    }

	
	  @GetMapping("/files/{filename:.+}")
	  
	  @ResponseBody public ResponseEntity<Resource> serveFile(@PathVariable String
	  filename) {
	  
	  Resource file = storageService.loadAsResource(filename); 
	  String  newfilename = file.getFilename().replaceAll("Input", "Output");
	  return 
	  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
	  "attachment; filename=\"" + newfilename + "\"").body(file); }
	 

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

    	String renameFileName = file.getOriginalFilename();
    	System.out.println(renameFileName + " original name");
    	
    	String renameFile = file.getName().replaceAll("Input", "Output");
    	//System.out.println(file.getOriginalFilename().replaceAll("Input", "Output") + " new file");
    	
    	storageService.store(file);
         
        
        // read csv file and write csv file
        Path filePath = storageService.load(file.getOriginalFilename());
        System.out.println("Reading");
        
        //CsvFileReader.readCsv(filePath.toString());
                
        System.out.println("Writting");
        CsvFileReader.writeCsv(filePath.toString());
        System.out.println("Writting");
        
        
        
		/*
		 * Rename and store
		 * 
		 * file.getOriginalFilename().replaceAll("Input", "Output");
		 * System.out.println(file.getName().toString() + " new file");
		 * storageService.store(file);
		 */        
        
        
        redirectAttributes.addFlashAttribute("message",
                "File successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
