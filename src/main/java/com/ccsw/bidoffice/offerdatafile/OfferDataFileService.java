package com.ccsw.bidoffice.offerdatafile;

public interface OfferDataFileService {

    Boolean checkExistsByFileTypeId(Long id);

    Boolean checkExistsByFormatDocumentId(Long id);
}
