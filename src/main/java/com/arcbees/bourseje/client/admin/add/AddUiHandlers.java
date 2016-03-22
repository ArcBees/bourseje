package com.arcbees.bourseje.client.admin.add;

import com.arcbees.bourseje.shared.Candidate;
import com.gwtplatform.mvp.client.UiHandlers;

public interface AddUiHandlers extends UiHandlers {
    void onAddCandidateClicked(Candidate candidate);
}
