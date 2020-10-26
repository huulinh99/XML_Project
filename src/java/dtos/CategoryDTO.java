/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CategoryDTO implements Serializable{
    public int id;
    public String name;
    public String url;
    public List<SubCategoryDTO> listSubCategory;

    public CategoryDTO() {
    }

    public CategoryDTO(int id, String name, String url, List<SubCategoryDTO> listSubCategory) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.listSubCategory = listSubCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<SubCategoryDTO> getListSubCategory() {
        return listSubCategory;
    }

    public void setListSubCategory(List<SubCategoryDTO> listSubCategory) {
        this.listSubCategory = listSubCategory;
    }
    
    
}
