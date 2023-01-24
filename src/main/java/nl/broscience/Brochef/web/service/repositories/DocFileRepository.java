package nl.broscience.Brochef.web.service.repositories;

import nl.broscience.Brochef.web.service.models.FileDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {
    FileDocument findByFileName(String fileName);
}
