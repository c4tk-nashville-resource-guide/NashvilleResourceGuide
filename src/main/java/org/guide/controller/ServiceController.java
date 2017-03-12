package org.guide.controller;

import org.guide.delegate.ServiceDelegate;
import org.guide.domain.ContributorRecord;
import org.guide.domain.SearchResult;
import org.guide.domain.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rgdavis on 3/12/17.
 */

@RestController
@RequestMapping("services")
public class ServiceController {

    @Autowired
    ServiceDelegate serviceDelegate;

    @RequestMapping(method = RequestMethod.GET)
    public List<SearchResult> getService(@RequestParam(value = "categoryId", required = false) String categoryId, @RequestParam(value = "zipCode", required = false) String zipCode){
        return serviceDelegate.findService(categoryId, zipCode);
    }

    @RequestMapping(value = "load", method = RequestMethod.POST)
    public void loadContributorRecords(@RequestBody List<ContributorRecord> records) {
        records.forEach(r -> serviceDelegate.loadRecord(r));
    }
}
