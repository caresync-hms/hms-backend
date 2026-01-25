package com.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.entity.Notice;
import com.backend.repository.NoticeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository noticeRepository;

	@Override
	public List<Notice> getAllNotices() {
		return noticeRepository.findAll();
	}

	@Override
	public Notice addNotice(Notice notice) {
		return noticeRepository.save(notice);
	}

	@Override
	public Notice updateNotice(Long noticeId, Notice notice) {

		Notice existingNotice = noticeRepository.findById(noticeId)
				.orElseThrow(() -> new RuntimeException("Notice not found"));

		existingNotice.setTitle(notice.getTitle());
		existingNotice.setNotice(notice.getNotice());
		existingNotice.setDate(notice.getDate());

		return noticeRepository.save(existingNotice);
	}

	@Override
	public void deleteNotice(Long noticeId) {
		Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new RuntimeException("Notice not found"));

		noticeRepository.delete(notice);
	}
}
