package ecom.icet.controller;

import ecom.icet.model.dto.BookingRequestDto;
import ecom.icet.model.dto.BookingResponseDto;
import ecom.icet.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BookingResponseDto> createBooking(
            @RequestPart("data") BookingRequestDto request,
            @RequestPart("cv") MultipartFile cvFile) {
        return ResponseEntity.ok(bookingService.createBooking(request, cvFile));
    }

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAll() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }
}
