package com.anodiam.CRUDStudentProfile.controller;

import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Board.BoardService;
import com.anodiam.CRUDStudentProfile.model.masterData.Board;
import com.anodiam.CRUDStudentProfile.model.masterData.Level;
import com.anodiam.CRUDStudentProfile.serviceRepository.masterData.Level.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/master-data")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReadMasterDataController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private LevelService levelService;

//  @GetMapping("boards") :: List all boards - to populate dropdowns in the frontend
    @GetMapping("/boards")
    @ResponseBody
    public List<Board> getAllBoards() throws Exception {
        try {
            return boardService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//  @GetMapping("levels") :: List all levels - to populate dropdowns in the frontend
    @GetMapping("/levels")
    @ResponseBody
    public List<Level> getAllLevels() throws Exception {
        try {
            return levelService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
