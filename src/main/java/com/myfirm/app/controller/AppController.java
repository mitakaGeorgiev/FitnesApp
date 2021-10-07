package com.myfirm.app.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

import com.myfirm.app.entity.Blog;
import com.myfirm.app.entity.Exercise;
import com.myfirm.app.entity.FileDB;
import com.myfirm.app.entity.Food;
import com.myfirm.app.entity.Log;
import com.myfirm.app.entity.PersonalProgram;
import com.myfirm.app.entity.PersonalRegime;
import com.myfirm.app.entity.User;
import com.myfirm.app.entity.UserRole;
import com.myfirm.app.entity.accountInfo;
import com.myfirm.app.fileUpload.FileUploadUtil;
import com.myfirm.app.fileUpload.ResponseMessage;
import com.myfirm.app.service.BlogRepository;
import com.myfirm.app.service.BlogService;
import com.myfirm.app.service.ExerciseRepository;
import com.myfirm.app.service.FoodRepository;
import com.myfirm.app.service.LogRepository;
import com.myfirm.app.service.PersonalProgramRepository;
import com.myfirm.app.service.PersonalRegimeRepository;
import com.myfirm.app.service.UserRepository;
import com.myfirm.app.service.UserRoleRepository;
import com.myfirm.app.service.accountInfoRepository;
 
@Controller
public class AppController {
    @Autowired
    private UserRepository userRepo; 
    @Autowired
     BlogService blogservice; 
    @Autowired
    private BlogRepository blogRepo;  
    @Autowired
    private LogRepository logRepo;
    @Autowired
    private accountInfoRepository accountInfoRepo;
    @Autowired
    private UserRoleRepository userRoleRepo;
    @Autowired
    private ExerciseRepository exerciseRepo;
    @Autowired
    private PersonalProgramRepository personalProgramRepo;
    @Autowired
    private PersonalRegimeRepository personalRegimeRepo;
    @Autowired
    private FoodRepository foodRepo;
    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }
    
    @PostMapping("/process_register")  
    public String processRegister(User user) {
    		User userByName = userRepo.findByUsername(user.getUsername());
    		User userByEmail = userRepo.findByEmail(user.getEmail());
    		if(userByName!=null ||userByEmail!=null){
                //If the query is found, the description exists
               return "failed_registration";
    		}
    		else {  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		        String encodedPassword = passwordEncoder.encode(user.getPassword());
		        user.setPassword(encodedPassword);
		        user.setRole(userRoleRepo.findByName("ROLE_USER"));
        
        
		         accountInfo accountinfo=new accountInfo();
		         user.setAccountinfo(accountinfo);
		         accountInfoRepo.save(accountinfo);
		         userRepo.save(user);
        
        
         
		         return "register_success";}
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        List<UserRole> listRoles = userRoleRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("listRoles", listRoles);
         
        return "users";
    }
    @GetMapping("login")
    public String login(Model model) {
     
        return "login";
    }
    
    @GetMapping("/users/changeRole") 
    public String saveNewRole(@RequestParam(value="userId") Long userid,@RequestParam(value="roleId") String role) {
    	User currentUser=userRepo.findByLongId(userid);
    	currentUser.setRole(userRoleRepo.findByName(role));
    	userRepo.save(currentUser);
    	return "index" ;
    }
    
    @GetMapping("/Knowledge")
    public String viewKnowledge(Model model) {
    	List<Blog> listBlogs = blogservice.findAll();
        model.addAttribute("listBlogs", listBlogs);
        
        return "blogBase";
    }
    @GetMapping("/Knowledge/createBlog")
    public String addBlog(Model model) {
    	Blog blog=new Blog();
    	
    	model.addAttribute("blog", blog);
    	return "createBlog";
    }
    @PostMapping("/blog_create")  
    public String processBlogCreate (Blog blog) {
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else {
     	    username = principal.toString();
     	  }
    	
  	 User currentUser=userRepo.findByUsername(username);
  	 blog.setUser(currentUser);
     blogRepo.save(blog);
     return "blogBase";
    }
    
    
    @GetMapping("/Knowledge/editBlog/{id}")
    public String editBlog(@PathVariable long id,Model model) {
    	 Optional<Blog> blog=Optional.ofNullable(new Blog());
    	 blog=blogRepo.findById(id);
    	 model.addAttribute("blog", blog);
    	 return "editBlog";
    }
    
    
    
    @PostMapping("/blog_change")  
    public String editBlog (Blog blog) {
    	blogRepo.save(blog);
    	
    	return "successBlogEdit";
    }
    
    @GetMapping("/Knowledge/deleteBlog/{id}")
    public String deleteBlog(@PathVariable long id,Model model) {
    	Optional<Blog> blog=Optional.ofNullable(new Blog());
    	 blog=blogRepo.findById(id);
    	 model.addAttribute("blog", blog);
    	return "deleteBlog";
    }
  
    
    
    @PostMapping("/blog_delete")  
    public String deleteBlog (Blog blog) {
    	blogRepo.delete(blog);
    	
    	return "successBlogDelete";
    }
    
    
    
    
    @GetMapping("/Calculator")
    public String viewBMICalculator(Model model) {
    	model.addAttribute("log", new Log());
    	return "bmiCalculator";
    	
    }
    
    @PostMapping("Calculator/log_create")
    	public String processLogCreate (Log log) {
    	String username;
 	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 	    if (principal instanceof UserDetails) {
    	    username = ((UserDetails)principal).getUsername();
    	   } else {
    	    username = principal.toString();
    	     }
 	  
 	   User currentUser=userRepo.findByUsername(username);
 	   accountInfo accountinfo= currentUser.getAccountinfo();
 	   log.setAccountinfo(accountinfo);
 	   log.setAge(currentUser.getAge());
       logRepo.save(log);
       return "bmiCalculated";
       
    }
    @RequestMapping("/bmiCalculated")
    public String BMICalculated() {
        return "bmiCalculated"; 
    }
    
    
    
    @GetMapping("/AccountInfo")
    public String viewAccountInfo(Model model) {
    	
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else  {
     	    username = principal.toString();
     	  		}
	  	User currentUser=userRepo.findByUsername(username);
    	model.addAttribute("user",currentUser);
    	
    	List<Log> listlogs=currentUser.getAccountinfo().getLogs();
    	model.addAttribute("listlogs",listlogs);
    	
    	List<Blog> listblogs=currentUser.getBlogs();
    	model.addAttribute("listblogs",listblogs);
    	
    	 
		PersonalProgram currentProgram=new PersonalProgram();
		
		 if(currentUser.getAccountinfo().getPersonalProgram()!=null)
		 { 
			 currentProgram=currentUser.getAccountinfo().getPersonalProgram(); 
			 Set<Exercise> setCurrentExercises=  currentProgram.getExercises();
		     List<Exercise> listCurrentExercises = new ArrayList<Exercise>(setCurrentExercises);
		     model.addAttribute("listCurrentExercises",listCurrentExercises);
		 }
		 else {
				 List<Exercise> listCurrentExercises = new ArrayList<Exercise>();
				 model.addAttribute("listCurrentExercises",listCurrentExercises);
		 	  }
		 model.addAttribute("personalProgram",currentUser.getAccountinfo().getPersonalProgram());
		 
		 PersonalRegime currentRegime=new PersonalRegime();
		 if(currentUser.getAccountinfo().getPersonalRegime()!=null)
		 { 
			 
			 currentRegime=currentUser.getAccountinfo().getPersonalRegime(); 
			 
			 Set<Food> setCurrentFoods=  currentRegime.getFoods();
			 
		     List<Food> listCurrentFoods = new ArrayList<Food>(setCurrentFoods);

		     model.addAttribute("listCurrentFoods",listCurrentFoods);
		 }
		 else {
				 List<Food> listCurrentFoods = new ArrayList<Food>();
				 model.addAttribute("listCurrentFood",listCurrentFoods);
		 	  }
		
		
		 
    	
    	return "accountInfo";
    }
    
    @GetMapping("/trainers")
    public String listTrainers(Model model) {
    	List<User> listUsers = userRepo.findAllByRole(userRoleRepo.findByName("ROLE_MENTOR"));
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", new User());
        
         
        return "trainers";
    }
    
   
    
   
    
    @GetMapping("/trainers/choose") 
    public String processTrainsersChoosing(@RequestParam(value="userId") Long mentorid) {
    	
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else {
     	    username = principal.toString();
     	  }
    	
  	 User currentUser=userRepo.findByUsername(username);
  	 currentUser.setMentorid(mentorid);
  	 
  	PersonalProgram personalProgram=new PersonalProgram();
  	 if(currentUser.getAccountinfo().getPersonalProgram()==null)
  	  {
  	 personalProgram.setDays(0);
  	 personalProgram.setBreakForSets(0);
  	 personalProgram.setSets(0);}
  	 else {
  		personalProgram=currentUser.getAccountinfo().getPersonalProgram();
  		personalProgram.setDays(0);
  		personalProgram.setBreakForSets(0);
  	  	personalProgram.setSets(0);
  		 
  	 }
  	 PersonalRegime personalRegime=new PersonalRegime();
 	 personalRegime.setDays(0);
    
 	 
 
  	 currentUser.getAccountinfo().setPersonalProgram(personalProgram);
  	 currentUser.getAccountinfo().setPersonalRegime(personalRegime);
  	 userRepo.save(currentUser);
  	 personalProgramRepo.save(personalProgram);
  	
    	return "trainerChoosing_success"; 
    }
    @GetMapping("/trainees")
    public String listTrainees(Model model) {
    	
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else {
     	    username = principal.toString();
     	  }
	  	User currentUser=userRepo.findByUsername(username);
    	long mentorid=currentUser.getId();
    	
    	List<User> listUsers = userRepo.findAllByMentorid(mentorid);
        model.addAttribute("listUsers", listUsers);
        
         
        return "trainees";
    }
    @GetMapping("/trainees/editProgram") 
    public String GettingTraineeIdProgram(@RequestParam(value="userId") Long traineeid,Model model) {
    	User trainee=userRepo.findByLongId(traineeid);
    	Long id=(trainee.getAccountinfo().getPersonalProgram().getPersonalProgram_id());
    	PersonalProgram personalProgram=new PersonalProgram();
    	personalProgram=personalProgramRepo.findByLongId(id);
    	
    	
    	model.addAttribute("personalProgram", personalProgram);
    	
    	List<Exercise> listExercises = exerciseRepo.findAll();
        model.addAttribute("listExercises", listExercises);
        
        Set<Exercise> currentExercises= personalProgram.getExercises();
        List<Exercise> listCurrentExercises = new ArrayList<Exercise>(currentExercises);
        
        
        model.addAttribute("testExercises", listCurrentExercises);
        
    	return "editProgram";  
    }
    @GetMapping("/trainees/editRegime") 
    public String GettingTraineeIdRegime(@RequestParam(value="userId") Long traineeid,Model model) {
   
    	User trainee=userRepo.findByLongId(traineeid);
    	Long id=(trainee.getAccountinfo().getPersonalRegime().getPersonalRegime_id());
    	
    	PersonalRegime personalRegime=new PersonalRegime();
    	personalRegime=personalRegimeRepo.findByLongId(id);
    	
    	
    	model.addAttribute("personalRegime", personalRegime);
    	List<Food> listFoods = foodRepo.findAll();
        model.addAttribute("listFoods", listFoods);
        
        Set<Food> currentFoods= personalRegime.getFoods();
        List<Food> listCurrentFoods = new ArrayList<Food>(currentFoods);
        
        
        model.addAttribute("testFoods", listCurrentFoods);        
    	return "editRegime";  
    }
    
    
    @RequestMapping("/trainer/Choosing_success")
    public String trainerChoosingSuccess() {
        return "trainerChoosing_success"; 
    }
    @RequestMapping("/trainees/editProgram/AddExercise")
    public String GettingExerciseName(@RequestParam(value="exercise") Long exerciseid,@RequestParam(value="programId") Long programid,Model model) {
    	
    	
    	PersonalProgram currentPersonalProgram=personalProgramRepo.findByLongId(programid);
    	Exercise CurrentExercise=exerciseRepo.findByLongId(exerciseid);
    	currentPersonalProgram.addExercise(CurrentExercise,  currentPersonalProgram);
    	personalProgramRepo.save(currentPersonalProgram);
    	
    	Set<Exercise> setAddedExercises=  personalProgramRepo.getById(programid).getExercises();	
    	List<Exercise> listCurrentExercises = new ArrayList<Exercise>(setAddedExercises);
    	 model.addAttribute("testExercises",listCurrentExercises);
    	 
    	
        return "editProgram :: testExercise-row"; 
        
   }
    @RequestMapping("/trainees/editRegime/AddFood")
    public String GettingFoodName(@RequestParam(value="food") Long foodid,@RequestParam(value="regimeId") Long regimeid,Model model) {
    	
    	
    	PersonalRegime currentPersonalRegime=personalRegimeRepo.findByLongId(regimeid);
    	Food CurrentFood=foodRepo.findByLongId(foodid);
    	currentPersonalRegime.addFood(CurrentFood,  currentPersonalRegime);
    	personalRegimeRepo.save(currentPersonalRegime);
    	
    	Set<Food> setAddedFoods=  personalRegimeRepo.getById(regimeid).getFoods();	
    	List<Food> listCurrentFoods = new ArrayList<Food>(setAddedFoods);
    	 model.addAttribute("testFoods",listCurrentFoods);
    	 
    	
        return "editRegime :: testFood-row"; 
        
   }
    @RequestMapping("/trainees/editProgram/RemoveExercise")
    public String RemovingExercise(@RequestParam(value="exercise") Long exerciseid,@RequestParam(value="programId") Long programid,Model model) {
    	
    	
    	PersonalProgram currentPersonalProgram=personalProgramRepo.findByLongId(programid);
    	Exercise CurrentExercise=exerciseRepo.findByLongId(exerciseid);
    	currentPersonalProgram.RemoveExercise(CurrentExercise,  currentPersonalProgram);
    	personalProgramRepo.save(currentPersonalProgram);
    	
    	Set<Exercise> setAddedExercises=  personalProgramRepo.getById(programid).getExercises();	
    	List<Exercise> listCurrentExercises = new ArrayList<Exercise>(setAddedExercises);
    	 model.addAttribute("testExercises",listCurrentExercises);
    	 
    	
        return "editProgram :: testExercise-row"; 
        
   }
    @RequestMapping("/trainees/editRegime/RemoveFood")
    public String RemovingFood(@RequestParam(value="food") Long foodid,@RequestParam(value="regimeId") Long regimeid,Model model) {
    	
    	
    	PersonalRegime currentPersonalRegime=personalRegimeRepo.findByLongId(regimeid);
    	Food CurrentFood=foodRepo.findByLongId(foodid);
    	currentPersonalRegime.RemoveFood(CurrentFood,  currentPersonalRegime);
    	personalRegimeRepo.save(currentPersonalRegime);
    	
    	Set<Food> setAddedFoods=  personalRegimeRepo.getById(regimeid).getFoods();	
    	List<Food> listCurrentFoods = new ArrayList<Food>(setAddedFoods);
    	 model.addAttribute("testFoods",listCurrentFoods);
    	 
    	
        return "editRegime :: testExercise-row"; 
        
   }
    @PostMapping("/trainees/editProgram/program_change")   
    public String editProgram (PersonalProgram personalProgram) {
    	personalProgramRepo.save(personalProgram);
    	
    	
    	return "successProgramEditing";
    }
    @PostMapping("/trainees/editRegime/regime_change")  
    public String editRegime (PersonalRegime personalRegime) {
    	personalRegimeRepo.save(personalRegime);
    	return "successRegimeEditing";
    }
    @GetMapping("/Knowledge/view/{id}")
    public String viewBlog(@PathVariable long id,Model model) {
    	Optional<Blog> blog=blogRepo.findById(id);
   	 model.addAttribute("blog", blog);
    return "blogView";
    }
    @GetMapping("/AccountInfo/addPicture")
    public String addProfilePicture(Model model) {
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else  {
     	    username = principal.toString();
     	  		}
	  	User currentUser=userRepo.findByUsername(username);
    	model.addAttribute("user",currentUser);
         
        return "uploadFile";
    }
    
    @PostMapping("/process_AddFile")
    public RedirectView saveUser(
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else  {
     	    username = principal.toString();
     	  		}
	  	User currentUser=userRepo.findByUsername(username);
	  	
         
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        currentUser.setPhoto(fileName);
        
        currentUser = userRepo.save(currentUser);
         
        String uploadDir = "user-photos/" + currentUser.getId();
 
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
         
        return new RedirectView("/trainers", true);
    }
    @GetMapping("/AccountInfo/MoreInfo")
    public String AddMoreAccountInfo(Model model) {
    	
    model.addAttribute("user", new User());
    
    return "detailedAccountInfo";
    }
    @PostMapping("/process_AddMoreInfo")  
    public String SaveMoreAccountInfo(User user) {
    	String username;
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  	if (principal instanceof UserDetails) {
        username = ((UserDetails)principal).getUsername();
     	} else {
     	    username = principal.toString();
     	  }
    	String mentorInfo=user.getMentorInfo();
  	 User currentUser=userRepo.findByUsername(username);
  	 currentUser.setMentorInfo(mentorInfo);
    userRepo.save(currentUser);
        
        
         
     return "moreInfo_success";}
    @RequestMapping(value="/Logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "redirect:/";
    }
}
