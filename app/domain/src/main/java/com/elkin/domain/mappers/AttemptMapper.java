package com.elkin.domain.mappers;

import com.elkin.commons.utils.Mapper;
import com.elkin.data.db.entities.Attempts;
import com.elkin.domain.models.AttemptModel;

public class AttemptMapper extends Mapper<Attempts, AttemptModel> {
    @Override
    public Attempts fromDto(AttemptModel dto) {
        Attempts attempt = new Attempts();
        attempt.setAid(dto.getAid());
        attempt.setDate(dto.getDate());
        attempt.setUserId(dto.getUserId());
        attempt.setResult(dto.getResult());
        return attempt;
    }

    @Override
    public AttemptModel fromEntity(Attempts entity) {
        AttemptModel attemptModel = new AttemptModel();
        attemptModel.setAid(entity.getAid());
        attemptModel.setUserId(entity.getUserId());
        attemptModel.setDate(entity.getDate());
        attemptModel.setResult(entity.getResult());
        return attemptModel;
    }
}
