package org.example.repository;


import org.example.domain.ReceiptSheet;

import java.util.List;

public interface ReceiptSheetRepository {
    List<ReceiptSheet> showReceiptSheet(long customerId);

}
