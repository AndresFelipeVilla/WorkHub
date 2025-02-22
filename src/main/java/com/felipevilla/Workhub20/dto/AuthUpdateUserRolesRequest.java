package com.felipevilla.Workhub20.dto;

import java.util.List;

public record AuthUpdateUserRolesRequest(String username, List<String> roleListName) { }

