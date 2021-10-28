package com.example.lab_01_v2.controller;

import com.example.lab_01_v2.forms.AlbumForm;
import com.example.lab_01_v2.forms.EditAlbumForm;
import com.example.lab_01_v2.model.Album;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
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

    @GetMapping(value = {"/", "/index"})
    //@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("message", message);
        log.info("/index was called");
        return  modelAndView;
    }

    @GetMapping(value = {"/allalbums"})
    //@RequestMapping(value = {"/allalbums"}, method = RequestMethod.GET)
    public ModelAndView personList(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        model.addAttribute("albums", albums);
        log.info("/allalbums was called");
        return modelAndView;
    }

    @GetMapping(value = {"/addalbum"})
    //@RequestMapping(value = {"/addalbum"}, method = RequestMethod.GET)
    public ModelAndView showAddPersonPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addalbum");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform", albumForm);
        log.info("/addalbum Get was called");
        return modelAndView;
    }

    @PostMapping("/addalbum")
    // GetMapping("/")
    //@RequestMapping(value = {"/addalbum"}, method = RequestMethod.POST)
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
        log.info("/allalbum post was called");
        modelAndView.setViewName("addalbum");
        model.addAttribute("errorMessage", errorMessage);
        return modelAndView;
    }

    //@RequestMapping(value = {"/delalbum"}, method = RequestMethod.GET)
    @GetMapping(value = {"/delalbum"})
    public ModelAndView showDelAlbumPage(Model model){
        ModelAndView modelAndView = new ModelAndView("delalbum");
        AlbumForm albumForm = new AlbumForm();
        model.addAttribute("albumform",albumForm);
        log.info("/delalbum GET was called");
        return  modelAndView;
    }

    @PostMapping(value = {"/delalbum"})
    //@RequestMapping(value = {"/delalbum"}, method = RequestMethod.POST)
    public ModelAndView delBook(Model model,
                                @ModelAttribute("albumform") AlbumForm albumForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String title = albumForm.getTitle();
        String author = albumForm.getAuthor();
        log.info("/delalbum POST was called");

        if(title!=null && title.length()>0
                && author != null && author.length()>0){
            int index = 0;
            for (Album album: albums){
                if(album.getTitle().equals(title) && album.getAuthor().equals(author)){
                    albums.remove(index);
                    break;
                }
                index++;
            }
            model.addAttribute("albums", albums);
            return modelAndView;
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("delalbum");
        return  modelAndView;
    }

    @GetMapping(value = {"/editalbum"})
    //@RequestMapping(value = {"/editalbum"}, method = RequestMethod.GET)
    public ModelAndView showEditAlbumPage(Model model){
        ModelAndView modelAndView = new ModelAndView("editalbum");
        EditAlbumForm albumForm = new EditAlbumForm();
        model.addAttribute("albumform",albumForm);
        log.info("/editalbum GET was called");
        return  modelAndView;
    }

    @PostMapping(value = {"/editalbum"})
    //@RequestMapping(value = {"/editalbum"}, method = RequestMethod.POST)
    public ModelAndView updateAlbum(Model model,
                                    @ModelAttribute("albumform") EditAlbumForm albumForm){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("albumlist");
        String title = albumForm.getTitle();
        String author = albumForm.getAuthor();
        String newTitle = albumForm.getNewTitle();
        String newAuthor = albumForm.getNewAuthor();
        log.info("/editalbum POST  was called");

        if(title!=null && title.length()>0
                && author != null && author.length()>0
                && newTitle != null && newTitle.length()>0
                && newAuthor !=null && newAuthor.length()>0){
            for (Album album: albums){
                if(album.getTitle().equals(title) && album.getAuthor().equals(author)){
                    album.setTitle(newTitle);
                    album.setAuthor(newAuthor);
                    model.addAttribute("albums", albums);
                    return modelAndView;
                }
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        modelAndView.setViewName("updatealbum");
        return  modelAndView;
    }
}
