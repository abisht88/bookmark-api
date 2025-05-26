package com.atb.bookmarker.api;

import com.atb.bookmarker.domain.BookmarkDTO;
import com.atb.bookmarker.domain.BookmarkService;
import com.atb.bookmarker.domain.BookmarksDTO;
import com.atb.bookmarker.domain.CreateBookmarkRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @GetMapping
    public BookmarksDTO getBookmarks(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                        @RequestParam(name = "query", defaultValue = "") String query) {
        if(query == null)
            return bookmarkService.getBookmarks(page);
        else
            return bookmarkService.searchBookmarks(page, query);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookmarkDTO createBookmark(@RequestBody @Valid CreateBookmarkRequest createBookmarkRequest){
        return bookmarkService.createBookmark(createBookmarkRequest);
    }
}
