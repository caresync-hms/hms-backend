package com.backend.service;

import java.util.List;

import com.backend.entity.Notice;

public interface NoticeService {

	List<Notice> getAllNotices();

	Notice addNotice(Notice notice);

	Notice updateNotice(Long noticeId, Notice notice);

	void deleteNotice(Long noticeId);
}
