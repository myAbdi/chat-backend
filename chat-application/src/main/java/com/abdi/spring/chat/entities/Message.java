package com.abdi.spring.chat.entities;

import java.util.Date;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import com.google.common.base.Strings;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
@Table("messages")
public class Message {
	@PrimaryKeyColumn(name = "username", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
	private String username;
	
	@PrimaryKeyColumn(name = "chatRoomId", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
	private String chatRoomId;
	 
	@PrimaryKeyColumn(name = "date", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
	private Date date;
	
	@Column
	private String fromUser;
	
	@Column
	private String toUser;
	
	@Column
	private String text;
	
	public Message() { 
		this.date = new Date();
	}
	
	public boolean isPublic() {
		return Strings.isNullOrEmpty(this.toUser);
	}
}
