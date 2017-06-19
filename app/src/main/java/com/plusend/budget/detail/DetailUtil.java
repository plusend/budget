package com.plusend.budget.detail;


import com.plusend.budget.model.Detail;

import java.util.List;

public interface DetailUtil {
    void saveDetail(Detail detail);

    List<Detail> listDetail();

    void removeDetail(Detail detail);

    void updateDetail(Detail detail);
}
