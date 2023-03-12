package googledrive.domain;

import googledrive.IndexerApplication;
import googledrive.domain.FileIndexed;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Index_table")
@Data
public class Index {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection
    private List<String> keywords;

    private Long fileld;

    @PostPersist
    public void onPostPersist() {
        FileIndexed fileIndexed = new FileIndexed(this);
        fileIndexed.publishAfterCommit();
    }

    public static IndexRepository repository() {
        IndexRepository indexRepository = IndexerApplication.applicationContext.getBean(
            IndexRepository.class
        );
        return indexRepository;
    }

    //<<< Clean Arch / Port Method
    public static void makeIndex(FileUploaded fileUploaded) {
        /** Example 1:  new item 
        Index index = new Index();
        repository().save(index);

        */

        /** Example 2:  finding and process
        
        repository().findById(fileUploaded.get???()).ifPresent(index->{
            
            index // do something
            repository().save(index);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
