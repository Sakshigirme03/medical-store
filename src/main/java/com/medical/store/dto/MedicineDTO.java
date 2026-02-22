package com.medical.store.dto;

public class MedicineDTO {

    private Long id;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;
    private boolean available;

    public MedicineDTO(Long id, String name, String manufacturer,
                       double price, int quantity, boolean available) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.available = available;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getManufacturer() { return manufacturer; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public boolean isAvailable() { return available; }
}
