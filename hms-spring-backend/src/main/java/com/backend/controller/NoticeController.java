package com.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entity.Notice;
import com.backend.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping
	public ResponseEntity<List<Notice>> getAllNotices() {
		return ResponseEntity.ok(noticeService.getAllNotices());
	}

	@PostMapping
	public ResponseEntity<Notice> addNotice(@RequestBody Notice notice) {
		return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.addNotice(notice));
	}

	@PutMapping("/{noticeId}")
	public ResponseEntity<Notice> updateNotice(@PathVariable Long noticeId, @RequestBody Notice notice) {

		return ResponseEntity.ok(noticeService.updateNotice(noticeId, notice));
	}

	@DeleteMapping("/{noticeId}")
	public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeId) {
		noticeService.deleteNotice(noticeId);
		return ResponseEntity.noContent().build();
	}
}
