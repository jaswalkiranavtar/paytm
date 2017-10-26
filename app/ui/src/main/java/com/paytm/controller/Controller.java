package com.paytm.controller;

import java.security.Principal;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.paytm.pojo.Events;
import com.paytm.pojo.ResponseCode;
import com.paytm.pojo.Result;

@RestController
public class Controller {
	
	@Autowired
	private Twitter twitter;

	@Autowired
    private ConnectionRepository connectionRepository;
	
//	@RequestMapping("/")
//    public ModelAndView getAdminPage()
//    {
//        return new ModelAndView("forward:/index.html");
//    }
	
	@RequestMapping("/logs")
	public Result<Events> getAllLogs(Principal user) {
		String encodedAuth = Base64.getEncoder().encodeToString("user:logger".getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Basic " + encodedAuth);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<Events> eventsResponse = new RestTemplate().exchange(
			"http://localhost:7777/event?user=" + user.getName(), 
			HttpMethod.GET, 
			new HttpEntity<Object>(httpHeaders), 
			Events.class
	    );
		return new Result<Events>(ResponseCode.SUCCESS, "Logs found", eventsResponse.getBody());
	}

	@RequestMapping("/")
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        return "hello";
    }
	
	@RequestMapping("/search-twitter")
	public Result<SearchResults> search(@RequestParam(value = "query") String query) {
		
		if (connectionRepository.findAllConnections().size() == 0) {
			return new Result<SearchResults>(ResponseCode.ERROR, "Please connect before searching", null);
		}
		SearchResults searchResults = null;
		
		try {
			searchResults = twitter.searchOperations().search(query);
		} catch(Exception ex) {
			return new Result<SearchResults>(ResponseCode.ERROR, "Error seraching: " + ex.getMessage(), null);
		}
		
		return new Result<SearchResults>(ResponseCode.SUCCESS, "Results Found", searchResults);
	}
}
