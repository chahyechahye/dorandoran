package com.doran.child;

import com.doran.child.entity.Child;
import com.doran.parent.entity.Parent;
import com.doran.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChildMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "parent",target = "parent")
    @Mapping(source = "user",target = "user")
    Child toChild(Parent parent, User user);
}
