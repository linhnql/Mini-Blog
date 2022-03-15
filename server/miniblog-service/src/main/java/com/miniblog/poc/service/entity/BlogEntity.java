package com.miniblog.poc.service.entity;

import com.miniblog.poc.api.model.Blog;
import com.miniblog.poc.api.model.BlogRequest;
import lombok.Data;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Indexed
@Entity
@Table(name = "BLOG")
@AnalyzerDef(name = "blogAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class ),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        }
)
@Data
public class BlogEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Field(analyzer = @Analyzer(definition = "blogAnalyzer"))
    @Column(name = "CATEGORY", nullable = false)
    private String category;

    @Field(analyzer = @Analyzer(definition = "blogAnalyzer"))
    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "IMAGE")
    private String image;

    @Column(name = "INTRO")
    private String intro;

    @Field(analyzer = @Analyzer(definition = "blogAnalyzer"))
    @Column(name = "DETAIL", nullable = false)
    private String detail;

    @Field(analyzer = @Analyzer(definition = "blogAnalyzer"))
    @Column(name = "CREATED_AT")
    private OffsetDateTime createdAt;

}
