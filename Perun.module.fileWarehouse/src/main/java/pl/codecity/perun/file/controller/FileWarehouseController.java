package pl.codecity.perun.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.codecity.perun.file.service.FileWarehouseService;

@Controller
public class FileWarehouseController {

    @Autowired
    private FileWarehouseService service;
}
