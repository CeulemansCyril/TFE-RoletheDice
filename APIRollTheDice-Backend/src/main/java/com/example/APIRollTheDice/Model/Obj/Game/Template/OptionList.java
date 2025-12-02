package com.example.APIRollTheDice.Model.Obj.Game.Template;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "option_lists")
public class OptionList {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private String name;



    @ElementCollection
    private List<String> options;

    @ManyToOne
    @JoinColumn(name = "template_field_id", nullable = false)
    private TemplateField templateField;

    public OptionList() {
    }
    public OptionList(long id, String name, List<String> options, TemplateField templateField) {
        this.id = id;
        this.name = name;
        this.options = options;
        this.templateField = templateField;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public TemplateField getTemplateField() {
        return this.templateField;
    }

    public void setTemplateField(TemplateField templateField) {
        this.templateField = templateField;
    }
}
