/*
 * Copyright 2016 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.arcbees.bourseje.client.admin.add;

import javax.inject.Inject;

import com.arcbees.bourseje.shared.Candidate;
import com.arcbees.ui.ReplacePanel;
import com.google.common.base.Strings;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import gwtupload.client.BaseUploadStatus;
import gwtupload.client.IFileInput;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.SingleUploader;

import static com.google.gwt.dom.client.BrowserEvents.CLICK;
import static com.google.gwt.query.client.GQuery.$;

public class AddView extends ViewWithUiHandlers<AddUiHandlers>
        implements AddPresenter.MyView, OnFinishUploaderHandler, ChangeHandler {
    interface Binder extends UiBinder<Widget, AddView> {
    }

    @UiField
    ButtonElement addCandidate;
    @UiField
    InputElement name;
    @UiField
    InputElement company;
    @UiField
    ReplacePanel uploaderPanel;
    @UiField
    ButtonElement cancel;

    private final ImagePlaceHolder imagePlaceHolder;

    private SingleUploader uploader;
    private String url;

    @Inject
    AddView(
            Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

        initButtons();

        imagePlaceHolder = new ImagePlaceHolder();
    }

    @Override
    protected void onAttach() {
        uploader = new SingleUploader(
                IFileInput.FileInputType.CUSTOM.withZone(imagePlaceHolder).with(imagePlaceHolder),
                new BaseUploadStatus(),
                null);
        uploader.setAutoSubmit(false);
        uploader.setValidExtensions(".jpg, .gif, .png, .jpeg");

        // handlers
        uploader.addOnFinishUploadHandler(this);
        uploader.getFileInput().addChangeHandler(this);

        uploaderPanel.setWidget(uploader);
    }

    @Override
    public void onChange(ChangeEvent event) {
        uploader.submit();
    }

    @Override
    public void onFinish(IUploader uploader) {
        url = Strings.nullToEmpty(uploader.getServerMessage().getMessage());

        imagePlaceHolder.setImageSource(url);
    }

    private void initButtons() {
        $(addCandidate).on(CLICK, new Function() {
            @Override
            public void f() {
                if (!name.getValue().isEmpty() && !company.getValue().isEmpty()) {
                    Candidate candidate = new Candidate(name.getValue(), company.getValue(), url);

                    getUiHandlers().onAddCandidateClicked(candidate);
                }
            }
        });

        $(cancel).on(CLICK, new Function() {
            @Override
            public void f() {
                getUiHandlers().onCancel();
            }
        });
    }
}
