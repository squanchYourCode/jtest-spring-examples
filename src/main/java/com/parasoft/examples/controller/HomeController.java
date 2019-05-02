package com.parasoft.examples.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * An example controller with a variety of MVC and non-MVC handler methods
 */
@Controller
@RequestMapping("/home")
public class HomeController
{
    /**
     * A Non-MVC handler method
     *
     * @return A view name
     */
    public String noMvcHome()
    {
        return "home.jsp";
    }

    /**
     * A Non-MVC handler method which uses Model and View
     *
     * @param model
     */
    public ModelAndView noMvcHomeModel(Model model)
    {
        return new ModelAndView("home.jsp");
    }

    /**
     * An MVC handler method for GET /home which sets a Model property.
     *
     * @param model
     */
    @GetMapping
    public ModelAndView mvcHome(Model model)
    {
        model.addAttribute("user", "Guest");
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method at a subpath
     *
     * @return A view name
     */
    @RequestMapping(path = "/sub")
    public String mvcSubpathHome()
    {
        return "homesub.jsp";
    }

    /**
     * An MVC handler method for POST /home, with a non-Spring parameter
     *
     * @param param1
     * @return A view name
     */
    @PostMapping()
    public String mvcPostHome(int param1)
    {
        return "home.jsp";
    }

    /**
     * An MVC handler method for POST /home/sub using RequestMapping
     *
     * @return A view name
     */
    @RequestMapping(path = "/sub", method = RequestMethod.POST)
    public String mvcSubpathPostHome()
    {
        return "home.jsp";
    }

    /**
     * An MVC handler method which uses a path parameter
     *
     * @param param1 The path segment value
     */
    @GetMapping("/{param1}")
    public ModelAndView mvcSubpathParam(@PathVariable int param1, Model model)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method which uses multiple path parameters
     */
    @GetMapping("/{param1}/{param2}")
    public ModelAndView mvcParams(@PathVariable int param1, @PathVariable String param2, Model model)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method which requires several query parameters
     *
     * @param model
     * @param param1 The value for query parameter param1
     * @param param2 The value for query parameter param2
     */
    @GetMapping("/sub")
    public ModelAndView mvcQueryParams(Model model, @RequestParam("param1") int param1, @RequestParam(name = "param2") String param2)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method which requires both path and query parameters
     *
     * @param path1 The path segment value
     * @param param1 The value for query parameter param1
     */
    @GetMapping("/sub/{path1}")
    public ModelAndView mvcMixedParams(Model model, @PathVariable int path1, @RequestParam(name = "param1") String param1)
    {
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method which accepts an HttpSession and sets a session attribute.
     *
     * @param session
     */
    @GetMapping("/session")
    public ModelAndView mvcWithSession(Model model, HttpSession session)
    {
        session.setAttribute("foo", "bar");
        return new ModelAndView("home.jsp", model.asMap());
    }

    /**
     * An MVC handler method which redirects to /home
     */
    @GetMapping("/redirect")
    public RedirectView doRedirect()
    {
        return new RedirectView("/home");
    }

    /**
     * An MVC handler method which requires a request HTTP header. The value of the header is added to the Model.
     *
     * @param myHeader The value of the MyHeader HTTP request header
     */
    @GetMapping("/headers")
    public ModelAndView mvcWithHeaders(Model model, @RequestHeader("MyHeader") String myHeader)
    {
        model.addAttribute("MyHeader", myHeader);
        return new ModelAndView("headers.jsp", model.asMap());
    }

    /**
     * An MVC handler method which accepts a Map containing all request HTTP headers. Each header is added to the Model.
     *
     * @param headers a Map of all request HTTP headers
     */
    @GetMapping("/headers_map")
    public ModelAndView mvcWithHeaders2(Model model, @RequestHeader MultiValueMap<String, String> headers)
    {
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            model.addAttribute(entry.getKey(), entry.getValue());
        }
        return new ModelAndView("headers.jsp", model.asMap());
    }

    /**
     * An MVC handler method which requires a request HTTP header, and sets the value of a response HTTP header.
     *
     * @param myRequestHeader The value of the MyRequestHeader HTTP request header
     * @return ResponseEntity containing the MyResponseHeader HTTP response header
     */
    @GetMapping("/headers_response")
    public ResponseEntity<String> mvcWithResponseHeaders(@RequestHeader("MyRequestHeader") String myRequestHeader)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.put("MyResponseHeader", Arrays.asList(myRequestHeader));
        return new ResponseEntity<>("", headers, HttpStatus.OK);
    }

    /**
     * An MVC handler method that returns a simple payload that contains a quoted string.
     */
    @GetMapping("/quotedString")
    public ResponseEntity<String> mvcWithQuotedStringInResponse()
    {
        return new ResponseEntity<>("This response has \"quotes\".", HttpStatus.OK);
    }
}
