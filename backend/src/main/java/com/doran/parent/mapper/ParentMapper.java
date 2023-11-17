package com.doran.parent.mapper;

import org.mapstruct.Mapper;

import com.doran.parent.entity.Parent;
import com.doran.parent.type.Provider;

@Mapper(componentModel = "spring")
public interface ParentMapper {
	Parent toParent(String email, Provider provider);

}
