package org.example.service.impl;

import org.example.domain.ReceiptSheet;
import org.example.repository.ReceiptSheetRepository;
import org.example.service.ReceiptSheetService;

import java.util.List;

public class ReceiptSheetServiceImpl implements ReceiptSheetService {
    private ReceiptSheetRepository receiptSheetRepository;

    public ReceiptSheetServiceImpl(ReceiptSheetRepository receiptSheetRepository) {
        this.receiptSheetRepository = receiptSheetRepository;
    }

    @Override
    public List<ReceiptSheet> showReceiptSheet(long customerId) {
        return receiptSheetRepository.showReceiptSheet(customerId);
    }
}
