package org.guide.delegate;

import org.guide.domain.ContributorRecord;
import org.guide.domain.SearchResult;
import org.guide.domain.Service;

import java.util.List;

/**
 * Created by rgdavis on 3/12/17.
 */
public interface ServiceDelegate {

    List<SearchResult> findService(String serviceId, String zip);
    void loadRecord(ContributorRecord record);
}
