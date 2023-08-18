package com.dukan.app.Tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRpo;

    public Tag createTag(Tag tag) {
        tagRpo.save(tag);
        return tagRpo.findById(tag.getTag()).orElse(null);
    }

}
