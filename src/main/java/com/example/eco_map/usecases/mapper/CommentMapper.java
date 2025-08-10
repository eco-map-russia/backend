package com.example.eco_map.usecases.mapper;

import com.example.eco_map.persistence.model.Comment;
import com.example.eco_map.persistence.model.User;
import com.example.eco_map.usecases.dto.CommentResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    @Mappings({
            @Mapping(source = "region.id", target = "regionId"),
            @Mapping(source = "user.id", target = "userId"),
            @Mapping(target = "username", expression = "java(getDisplayName(comment.getUser()))")

    })
    CommentResponseDto toCommentResponseDto(Comment comment);


    default String getDisplayName(User user) {
        String first = user.getFirstName();
        String last = user.getLastName();

        boolean hasFirst = first != null && !first.isBlank();
        boolean hasLast = last != null && !last.isBlank();

        if (hasLast && hasFirst) {
            return capitalize(last) + " " + first.trim().toUpperCase().charAt(0) + ".";
        } else if (hasLast) {
            return capitalize(last);
        } else if (hasFirst) {
            return capitalize(first);
        } else {
            return "Неизвестный пользователь";
        }
    }

    private String capitalize(String s) {
        s = s.trim();
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
