package com.atb.bookmarker.domain;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.time.Instant.now;

@Service
@Transactional
@RequiredArgsConstructor
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;

    @Transactional(readOnly = true)
    public BookmarksDTO getBookmarks(Integer page) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.DESC, "id");
        Page<BookmarkDTO> bookmarkDTOPage = bookmarkRepository.getAllBookMarks(pageable);
        return new BookmarksDTO(bookmarkDTOPage);
    }

    @Transactional(readOnly = true)
    public BookmarksDTO searchBookmarks(Integer page, String query) {
        int pageNo = page < 1 ? 0 : page - 1;
        Pageable pageable = PageRequest.of(pageNo, 10, Sort.Direction.ASC, "id");
        Page<BookmarkDTO> bookmarkDTOPage = bookmarkRepository.findByTitleContainsIgnoreCase(query, pageable);
        return new BookmarksDTO(bookmarkDTOPage);
    }

    public BookmarkDTO createBookmark(CreateBookmarkRequest createBookmarkRequest) {
        Bookmark bookmark = new Bookmark(null, createBookmarkRequest.getTitle(),
                createBookmarkRequest.getUrl(), now());
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);
        return bookmarkMapper.toDTO(savedBookmark);
    }
}
