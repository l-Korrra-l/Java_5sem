package com.example.springbooks.controller;
import com.example.springbooks.forms.AlbumForm;
import com.example.springbooks.model.Album;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import  java.util.ArrayList;
import  java.util.List;

@Controller
public class AlbumController {
    private static List<Album> albums = new ArrayList<Album>();
    static {
        albums.add(new Album("Frst", "Author"));
        albums.add(new Album("Sec", "Author_2"));
    }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);

        return  modelAndView;
    }

    @RequestMapping(value = {"/allalbums"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        model.addAttribute("albums", albums);
        return modelAndView; }

    @RequestMapping(value = {"/addalbum"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addalbum");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);

        return modelAndView;
    }
    // @PostMapping("/addbook")
    // GetMapping("/")
    @RequestMapping(value = {"/addalbum"}, method = RequestMethod.POST)
    public ModelAndView savePerson(Model model, @ModelAttribute("albumform") AlbumForm albumForm) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String title = albumForm.getTitle();
        String author = albumForm.getAuthor();
        if (title != null && title.length() > 0 && author != null && author.length() > 0) {
            Album newAlbum = new Album(title, author);
            albums.add(newAlbum);
            model.addAttribute("albums",albums);
            return modelAndView;
        }

        modelAndView.setViewName("addalbum");
        model.addAttribute("errorMessage", errorMessage);
        return modelAndView;
    }

    @RequestMapping(value = {"/editalbum/{id}"}, method = RequestMethod.GET)
    public ModelAndView showEditAlbumPage(Model model,@PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("editalbum");
        Album album = albums.get(id);
        AlbumForm albumForm = new AlbumForm(album.getTitle(), album.getAuthor());
        model.addAttribute("albumform", albumForm);

        return  modelAndView;
    }

    @RequestMapping(value = {"/editalbum/{id}"}, method = RequestMethod.POST)
    public ModelAndView EditAlbum(Model model,@PathVariable(value = "id") int id, @ModelAttribute("albumform") AlbumForm albumForm) {
        ModelAndView modelAndView = new ModelAndView("albumlist");
        Album album = albums.get(id);
        album.setTitle(albumForm.getTitle());
        album.setAuthor(albumForm.getAuthor());
        model.addAttribute("albums", albums);

        return  modelAndView;
    }

    @RequestMapping(value = {"/deletealbum/{id}"}, method = RequestMethod.POST)
    public ModelAndView deleteAlbum(Model model,@PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView("albumlist");
        Album album = albums.remove(id);
        model.addAttribute("albums", albums);

        return  modelAndView;
    }
}
