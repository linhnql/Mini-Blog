package com.miniblog.poc.service.entity;

import lombok.Data;
import org.apache.lucene.analysis.core.KeywordTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Indexed
@Entity
@Table(name = "CONTACT")
@AnalyzerDef(name = "contactAnalyzer",
        tokenizer = @TokenizerDef(factory = KeywordTokenizerFactory.class ),
        filters = {
                @TokenFilterDef(factory = StandardFilterFactory.class),
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
        }
)
@Data

public class ContactEntity {

    @Id
    @Column(name = "ID", nullable = false)
    private String id;

    @Field(analyzer = @Analyzer(definition = "contactAnalyzer"))
    @Column(name = "NAME", nullable = false)
    private String name;

    @Field(analyzer = @Analyzer(definition = "contactAnalyzer"))
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "MESSENGER", nullable = false)
    private String messenger;

    @Field(analyzer = @Analyzer(definition = "contactAnalyzer"))
    @Column(name = "SENT_AT")
    private OffsetDateTime sentAt;

}
