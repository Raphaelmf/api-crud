package br.com.model;

import java.time.Instant;
import java.util.UUID;

public class MessageModel {
	
	private UUID id;
	private UUID conversationId;
	private String name;
	private Instant timestampMsg;
	private UUID fromId;
	private UUID toId;
	private String textMsg;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getConversationId() {
		return conversationId;
	}
	public void setConversationId(UUID conversationId) {
		this.conversationId = conversationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Instant getTimestampMsg() {
		return timestampMsg;
	}
	public void setTimestampMsg(Instant timestampMsg) {
		this.timestampMsg = Instant.now();
	}
	public UUID getFromId() {
		return fromId;
	}
	public void setFromId(UUID fromId) {
		this.fromId = fromId;
	}
	public UUID getToId() {
		return toId;
	}
	public void setToId(UUID toId) {
		this.toId = toId;
	}
	public String getTextMsg() {
		return textMsg;
	}
	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

}
