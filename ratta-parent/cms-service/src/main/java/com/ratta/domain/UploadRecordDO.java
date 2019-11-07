package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author gxy
 *
 */
@Data
public class UploadRecordDO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String innerName;

	private Date createTime;

	private String createUser;

}