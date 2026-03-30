package com.example.APIRollTheDice.Model.Obj.Game.Template;

import jakarta.persistence.*;

@Entity
@Table(name = "custom_object_attribute")
public class CustomObjectAttribute {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "custom_object_id", nullable = false)
    private CustomObject customObject;

    @ManyToOne
    @JoinColumn(name = "template_field_id", nullable = false)
    private TemplateField templateField;

    @Column(nullable = false)
    private String value;

    @Column(nullable = false)
    private String type;

    public CustomObjectAttribute() {
    }

    public CustomObjectAttribute(Long id, CustomObject customObject, TemplateField templateField, String value, String type) {
        this.id = id;
        this.customObject = customObject;
        this.templateField = templateField;
        this.value = value;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomObject getCustomObject() {
        return customObject;
    }

    public void setCustomObject(CustomObject customObject) {
        this.customObject = customObject;
    }

    public TemplateField getTemplateField() {
        return templateField;
    }

    public void setTemplateField(TemplateField templateField) {
        this.templateField = templateField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
