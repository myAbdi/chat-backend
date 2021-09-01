package com.abdi.spring.chat.entities;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table("messages")
public class Message {
	@PrimaryKey
	private Key key;
	@Column
	private String fromUser;
	@Column
	private String toUser;
	@Column
	private String text;
}
