package hwan.board.board_project.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTime {
    
    //Entity가 생성되어 저장될 때 시간이 자동 저장됨.
    @CreatedDate
    protected LocalDateTime createdDate;

    // 조회한 entity 값을 변경할 때 시간이 자동 저장됨.
    @LastModifiedDate
    protected LocalDateTime modifiedDate;
}
