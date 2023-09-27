package com.securityaws.apirest.service;

import com.securityaws.apirest.controllers.BooksController;
import com.securityaws.apirest.data.vo.v1.BooksVO;
import com.securityaws.apirest.data.vo.v1.dozermapper.DozerMapper;
import com.securityaws.apirest.exception.ErrorResponse;
import com.securityaws.apirest.exception.RequiredObjectsNullException;
import com.securityaws.apirest.model.Books;
import com.securityaws.apirest.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;


    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(BooksService.class.getName());

    public List<BooksVO> findAllBooks(){
        var books = DozerMapper.parseListObject(booksRepository.findAll(), BooksVO.class);

        books
                .stream()
                .forEach(p -> p.add(linkTo(methodOn(BooksController.class).getById(p.getKey())).withSelfRel()));

        return books;
    }
    public BooksVO getByidBooks(Long id){
        Optional<Books> booksOptional =  booksRepository.findById(id);
        if (booksOptional.isPresent()){

            var vo =  DozerMapper.parseObject(booksOptional.get(), BooksVO.class);

            vo.add(linkTo(methodOn(BooksController.class).getById(id)).withSelfRel());

            return vo;

        } else {
            throw new ErrorResponse(String.format("Books com id: '%s' não encontrado", id));
        }
    }
    public BooksVO createBooks(BooksVO booksvo){

        if(booksvo == null) throw  new RequiredObjectsNullException();

        var entity = DozerMapper.parseObject(booksvo, Books.class);

        var vo =DozerMapper.parseObject(booksRepository.save(entity), BooksVO.class);

        vo.add(linkTo(methodOn(BooksController.class).getById(vo.getKey())).withSelfRel());

        return vo;
    }

    public BooksVO updateBooks(BooksVO books){

        if(books == null) throw  new RequiredObjectsNullException();

        Books existingBooks = booksRepository.findById(books.getKey())
                        .orElseThrow(() -> new ErrorResponse("id inválido"));

        existingBooks.setAuthor(books.getAuthor());
        existingBooks.setLaunchDate(books.getLaunchDate());
        existingBooks.setPrice(books.getPrice());
        existingBooks.setTitle(books.getTitle());

        var vo =DozerMapper.parseObject(booksRepository.save(existingBooks), BooksVO.class);

        vo.add(linkTo(methodOn(BooksController.class).getById(vo.getKey())).withSelfRel());

        return vo;
    }

    public void deleteById(Long id) {
        getByidBooks(id);
        booksRepository.deleteById(id);
    }

}
