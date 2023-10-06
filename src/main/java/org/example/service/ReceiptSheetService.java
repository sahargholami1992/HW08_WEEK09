package org.example.service;

import org.example.domain.ReceiptSheet;

import java.util.List;

public interface ReceiptSheetService {
    List<ReceiptSheet> showReceiptSheet(long customerId);
}
