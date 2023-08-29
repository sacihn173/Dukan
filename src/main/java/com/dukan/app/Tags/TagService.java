package com.dukan.app.Tags;

import com.dukan.app.Product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRpo;

    public Tag createTag(Tag tag) {
        tagRpo.save(tag);
        return tagRpo.findById(tag.getTag()).orElse(null);
    }

    public Set<Product> findProductsByTag(String tag) {
        Tag t = tagRpo.findById(tag).orElse(null);
        if(t != null)
            return t.getProducts();
        return new HashSet<>();
    }

    public List<String> getProductTags(List<Integer> productIds) {
        return tagRpo.getProductTags(productIds);
    }

}
