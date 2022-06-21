package com.example.demo.controller;

import com.example.demo.dto.InvoiceCreatingDto;
import com.example.demo.model.BilledProduct;
import com.example.demo.model.Invoice;
import com.example.demo.model.Patient;
import com.example.demo.repository.BilledProductRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@RestController
public class InvoiceController {
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    BilledProductRepository billedProductRepository;
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

    @PostMapping("Invoice/Add")
    public Invoice addInvoice(@RequestBody InvoiceCreatingDto invoiceDto){
        Invoice invoice = new Invoice();
        Patient patient = patientRepository.findPatientByCin(invoiceDto.getPatientCin());
        AtomicReference<Long> totalAmount = new AtomicReference<>(Long.valueOf(0));
        invoice.setPatient(patient);
        invoice.setBillNo(invoiceDto.getBillNumber());
        invoice.setDiscount(invoiceDto.getDiscountValue());
        invoice.setAdmissionId(invoiceDto.getAdmissionID());
        invoice.setAdmitDate(invoiceDto.getAdmissionDate());
        invoice.setDischargeDate(invoiceDto.getDischargeDate());
        invoice.setPaymentMethod(invoiceDto.getPaymentMethod());
        invoice.setPaymentStatus(invoiceDto.getPaymentStatus());
        invoiceDto.getProducts().stream().forEach(
                (p -> {
                    BilledProduct billedProduct = new BilledProduct();
                    billedProduct.setInvoice(invoice);
                    billedProduct.setName(p.getName());
                    billedProduct.setPrice(p.getPrice());
                    billedProduct.setQty(p.getQty());
                    this.billedProductRepository.saveAndFlush(billedProduct);
                })
        );
        invoiceDto.getProducts().stream().forEach(
                (p -> {
                    totalAmount.updateAndGet(v -> v + p.getPrice() * p.getQty());
                })
        );
        Long TA = totalAmount.get();
        invoice.setTotalAmount(TA);
        return this.invoiceRepository.save(invoice);
     }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
    @GetMapping("Invoice/All")
    public List<InvoiceCreatingDto> getAllInvoices(){
       List<InvoiceCreatingDto> invoiceCreatingDtoList =  this.invoiceRepository.findAll().stream().map(Invoice->{
            InvoiceCreatingDto invoiceCreatingDto = new InvoiceCreatingDto();
            invoiceCreatingDto.setId(Invoice.getId());
            invoiceCreatingDto.setPatientName(Invoice.getPatient().getName());
            invoiceCreatingDto.setAdmissionID(Invoice.getAdmissionId());
            invoiceCreatingDto.setDischargeDate(Invoice.getDischargeDate());
            invoiceCreatingDto.setDiscountValue(Invoice.getDiscount());
            invoiceCreatingDto.setAdmissionDate(Invoice.getAdmitDate());
//            invoiceCreatingDto.setProducts();
            List<BilledProduct> products = this.billedProductRepository.getProductsByInvoiceId(Invoice.getId());
            invoiceCreatingDto.setProducts(products);
            invoiceCreatingDto.setPaymentMethod(Invoice.getPaymentMethod());
            invoiceCreatingDto.setPatient(Invoice.getPatient());
            invoiceCreatingDto.setBillNumber(Invoice.getBillNo());
            invoiceCreatingDto.setTotal(Invoice.getTotalAmount());
            invoiceCreatingDto.setPaymentStatus(Invoice.getPaymentStatus());
            return invoiceCreatingDto;
        }).collect(Collectors.toList());
       return  invoiceCreatingDtoList;
     }
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

    @PutMapping("Invoice/Edit/{id}")
     public Integer editInvoice(@RequestBody InvoiceCreatingDto invoiceDto,@PathVariable("id") Long id){
        return this.invoiceRepository.updateInvoice(id
                ,invoiceDto.getAdmissionID(),
                invoiceDto.getPaymentStatus(),
                invoiceDto.getAdmissionDate(),
                invoiceDto.getDiscountValue(),
                invoiceDto.getPaymentMethod());

     }

     @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
     @DeleteMapping("Invoice/Delete/{id}")
     public void deleteInvoice(@PathVariable("id") Long id){
        this.invoiceRepository.deleteById(id);
     }
}
