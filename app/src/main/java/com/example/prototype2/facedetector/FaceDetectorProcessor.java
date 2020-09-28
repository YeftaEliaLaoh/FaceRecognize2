/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use / file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.prototype2.facedetector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.prototype2.GraphicOverlay;
import com.example.prototype2.VisionProcessorBase;
import com.example.prototype2.databases.AppDatabases;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.face.Face;
import com.google.mlkit.vision.face.FaceDetection;
import com.google.mlkit.vision.face.FaceDetector;
import com.google.mlkit.vision.face.FaceDetectorOptions;
import com.google.mlkit.vision.face.FaceLandmark;

import java.util.List;
import java.util.Locale;

/**
 * Face Detector Demo.
 */
public class FaceDetectorProcessor extends VisionProcessorBase<List<Face>> {

    private static final String TAG = "FaceDetectorProcessor";

    private final FaceDetector detector;

    private Bitmap originalCameraImage = null;

    public FaceDetectorProcessor(Context context, FaceDetectorOptions options) {
        super(context);
        Log.v(MANUAL_TESTING_LOG, "Face detector options: " + options);
        detector = FaceDetection.getClient(options);
    }

    @Override
    public void stop() {
        super.stop();
        detector.close();
    }

    @Override
    protected Task<List<Face>> detectInImage(InputImage image, Bitmap originalCameraImage) {
        this.originalCameraImage = originalCameraImage;
        return detector.process(image);
    }

    @Override
    protected void onSuccess(@NonNull List<Face> faces, @NonNull GraphicOverlay graphicOverlay, AppDatabases appDatabases, String page) {
        for (Face face : faces) {
            // graphicOverlay.add(new FaceGraphic(graphicOverlay, face));
            logExtrasForTesting(face, graphicOverlay, appDatabases, page);
        }
    }

    private void logExtrasForTesting(Face face, GraphicOverlay graphicOverlay, AppDatabases appDatabases, String page) {
        if (face != null) {
            Log.v(MANUAL_TESTING_LOG, "face bounding box: " + face.getBoundingBox().flattenToString());
            Log.v(MANUAL_TESTING_LOG, "face Euler Angle X: " + face.getHeadEulerAngleX());
            Log.v(MANUAL_TESTING_LOG, "face Euler Angle Y: " + face.getHeadEulerAngleY());
            Log.v(MANUAL_TESTING_LOG, "face Euler Angle Z: " + face.getHeadEulerAngleZ());

            // All landmarks
            int[] landMarkTypes =
                    new int[]{
                            FaceLandmark.MOUTH_BOTTOM,
                            FaceLandmark.MOUTH_RIGHT,
                            FaceLandmark.MOUTH_LEFT,
                            FaceLandmark.RIGHT_EYE,
                            FaceLandmark.LEFT_EYE,
                            FaceLandmark.RIGHT_EAR,
                            FaceLandmark.LEFT_EAR,
                            FaceLandmark.RIGHT_CHEEK,
                            FaceLandmark.LEFT_CHEEK,
                            FaceLandmark.NOSE_BASE
                    };
            String[] landMarkTypesStrings =
                    new String[]{
                            "MOUTH_BOTTOM",
                            "MOUTH_RIGHT",
                            "MOUTH_LEFT",
                            "RIGHT_EYE",
                            "LEFT_EYE",
                            "RIGHT_EAR",
                            "LEFT_EAR",
                            "RIGHT_CHEEK",
                            "LEFT_CHEEK",
                            "NOSE_BASE"
                    };
            for (int i = 0; i < landMarkTypes.length; i++) {
                FaceLandmark landmark = face.getLandmark(landMarkTypes[i]);
                if (landmark == null) {
                    Log.v(
                            MANUAL_TESTING_LOG,
                            "No landmark of type: " + landMarkTypesStrings[i] + " has been detected");
                } else {
                    PointF landmarkPosition = landmark.getPosition();
                    String landmarkPositionStr =
                            String.format(Locale.US, "x: %f , y: %f", landmarkPosition.x, landmarkPosition.y);
                    Log.v(
                            MANUAL_TESTING_LOG,
                            "Position for face landmark: "
                                    + landMarkTypesStrings[i]
                                    + " is :"
                                    + landmarkPositionStr);
                }
            }
            Log.v(
                    MANUAL_TESTING_LOG,
                    "face left eye open probability: " + face.getLeftEyeOpenProbability());
            Log.v(
                    MANUAL_TESTING_LOG,
                    "face right eye open probability: " + face.getRightEyeOpenProbability());
            Log.v(MANUAL_TESTING_LOG, "face smiling probability: " + face.getSmilingProbability());
            Log.v(MANUAL_TESTING_LOG, "face tracking id: " + face.getTrackingId());

            if (page.equalsIgnoreCase("register")) {
                //FaceEntity faceEntity = new FaceEntity();
                float left = (float) (face.getBoundingBox().width() * 0.2);
                float newWidth = (float) (face.getBoundingBox().width() * 0.6);

                float top = (float) (face.getBoundingBox().height() * 0.2);
                float newHeight = (float) (face.getBoundingBox().height() * 0.6);

                Bitmap.createBitmap(originalCameraImage,
                        ((int) (left)),
                        (int) (top),
                        ((int) (newWidth)),
                        (int) (newHeight));

                /*long id = appDatabases.faceDao().insertNewEntry(faceEntity);
                Toast.makeText(graphicOverlay.getContext(),
                        "Set image with id: " + id,
                        Toast.LENGTH_LONG)
                        .show();*/
            }/* else if (page.equalsIgnoreCase("login")) {
                List<FaceEntity> faceEntityList = appDatabases.faceDao().getAllByFace(face.getBoundingBox().left,
                        face.getBoundingBox().top,
                        face.getBoundingBox().right,
                        face.getBoundingBox().bottom);
                if (faceEntityList.isEmpty()) {
                    Toast.makeText(graphicOverlay.getContext(),
                            "Get image with id: ",
                            Toast.LENGTH_LONG)
                            .show();
                } else {
                    Toast.makeText(graphicOverlay.getContext(),
                            "Get image with id: " + faceEntityList.get(0).getId(),
                            Toast.LENGTH_LONG)
                            .show();
                }
            }*/
        }
    }

    @Override
    protected void onFailure(@NonNull Exception e) {
        Log.e(TAG, "Face detection failed " + e);
    }
}
