package ru.gb.springdemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.springdemo.model.Book;
import ru.gb.springdemo.model.Issue;
import ru.gb.springdemo.model.Reader;
import ru.gb.springdemo.repository.BookRepository;
import ru.gb.springdemo.repository.IssueRepository;
import ru.gb.springdemo.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }
    public Reader getReaderById(long readrId) {
        return readerRepository.findById(readrId)
                .orElseThrow(NoSuchElementException::new);
    }
    public void removeReaderById(long readrId) {
        readerRepository.deleteById(readrId);
    }

    public List<Issue> getAllIssueReader(long readerId) {
        return issueRepository.findByReturned(null)
                .stream()
                .filter(issue -> issue.getReaderId() == readerId)
                .collect(Collectors.toList());
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public List<Book> getAllBooksReader(long readerId) {
        return getAllIssueReader(readerId).stream()
                .mapToLong(Issue::getBookId)
                .mapToObj(bookRepository::findById)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}