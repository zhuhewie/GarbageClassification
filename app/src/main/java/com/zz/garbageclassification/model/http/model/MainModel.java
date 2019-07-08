package com.zz.garbageclassification.model.http.model;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import com.zz.garbageclassification.BuildConfig;
import com.zz.garbageclassification.Constants;
import com.zz.garbageclassification.model.bean.Token;
import com.zz.garbageclassification.model.bean.response.*;
import com.zz.garbageclassification.model.http.HttpUrl;
import com.zz.garbageclassification.model.http.RetrofitManager;
import com.zz.garbageclassification.model.rx.RetryLogin;
import com.zz.garbageclassification.util.RxUtil;
import com.zz.garbageclassification.util.StringUtil;
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils;
import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.reactivestreams.Publisher;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> 文件描述 : <p>
 * <p> 作者 : zhuhewie <p>
 * <p> 创建时间 : 2019/3/12 <p>
 * <p> 更改时间 : 2019/3/12 <p>
 * <p> 版本号 : 1 <p>
 */
public class MainModel {




    /**
     * jian检查更新
     */

    public Flowable<ResponseBody> update(String version){
        return RetrofitManager.INSTANCE.getMainService()
                .update(version, Constants.platform)
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }

    public Flowable<ResponseBody> changePwd(String oldPwd,  String newPwd) {

        return RetrofitManager.INSTANCE.getMainService()
                .changePwd(Token.Companion.getInstance().getBeareeToken(),
                        oldPwd,
                        newPwd)
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());

    }


    /**
     * 仲裁员身份、信息提交状态
     *
     * @return
     */
    @SuppressLint("CheckResult")
    public Flowable<ArbitratorResponse> getArbitrator() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ArbitratorResponse>>() {
                    @Override
                    public Publisher<ArbitratorResponse> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getArbitratorMsg(token);
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ArbitratorResponse>ioToMain());
    }

    /**
     * 获取菜单列表
     * 首页/我的
     *
     * @return
     */
    public Flowable<List<MenusResponse>> getMenus(final String pageType) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<List<MenusResponse>>>() {
                    @Override
                    public Publisher<List<MenusResponse>> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getMenus(
                                token,
                                pageType,
                                BuildConfig.VERSION_NAME,
                                Constants.platform);
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<List<MenusResponse>>ioToMain());
    }
    /**
     * 获取仲裁员信息
     *
     * @return
     */
    public Flowable<InfoResponse> getInfo() {
//        getInfoBody();
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<InfoResponse>>() {
                    @Override
                    public Publisher<InfoResponse> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getInfo(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<InfoResponse>ioToMain());
    }

    public void getInfoBody() {
         Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getInfoBody(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain())
                 .subscribe(new Consumer<ResponseBody>() {
                     @Override
                     public void accept(ResponseBody responseBody) throws Exception {

                         Log.d("test",responseBody.string());
                     }
                 }, new Consumer<Throwable>() {
                     @Override
                     public void accept(Throwable throwable) throws Exception {

                     }
                 });
    }


    /**
     * 获取仲裁员基本信息
     *
     * @return
     */
    public Flowable<BasicInfoResponse> getBasicInfo() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<BasicInfoResponse>>() {
                    @Override
                    public Publisher<BasicInfoResponse> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getBasicInfo(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<BasicInfoResponse>ioToMain());
    }

    /**
     * 获取庭室导航列表
     *
     * @return
     */
    public Flowable<List<AddressResponse>> getAddressList() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<List<AddressResponse>>>() {
                    @Override
                    public Publisher<List<AddressResponse>> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getAddressNavigationList(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<List<AddressResponse>>ioToMain());
    }

    /**
     * 新闻动态列表
     *
     * @param length      每一页长度
     * @param currentPage 页数
     * @return
     */
    public Flowable<NewsResponse> getNews(final String length, final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<NewsResponse>>() {
                    @Override
                    public Publisher<NewsResponse> apply(String token) throws Exception {
                        return RetrofitManager.INSTANCE.getMainService().getNews(
                                token,
                                length,
                                currentPage
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<NewsResponse>ioToMain());
    }

    /**
     * 获取微信动态列表
     *
     * @param length
     * @param currentPage
     * @return
     */
    public Flowable<NewsResponse> getWechatNews(final String length, final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<NewsResponse>>() {
                    @Override
                    public Publisher<NewsResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getWechatNews(
                                token,
                                length,
                                currentPage
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<NewsResponse>ioToMain());
    }

    /**
     * 获取 签名或待签名  案件列表,
     * @return
     */
    public Flowable<SignCaseResponse<SignCase>> getSignList(final boolean sign, final String pageSize, final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<SignCaseResponse<SignCase>>>() {
                    @Override
                    public Publisher<SignCaseResponse<SignCase>> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getSignList(
                                token,
                                sign,
                                pageSize,
                                currentPage
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<SignCaseResponse<SignCase>>ioToMain());
    }


    /**
     * 预览待签名文件
     * @param mainInfoCid 按键ID
     * @param docType
     * @return
     */
    public Flowable<ResponseBody> readPdf(final String mainInfoCid, final String docType) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {

                        String docTypeString = docType;
                        if (docType.contains("裁决")) {
                            docTypeString="Arbitrament";
                        } else if (docType.contains("调解")){
                            docTypeString="Mediation";
                        } else if ("决定书".equals(docType) || "撤回".equals(docType)) {
                            docTypeString="DrawBack";
                        }

                        return  RetrofitManager.INSTANCE.getMainService().readPdf(
                                token,
                                mainInfoCid,
                                docTypeString
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }

    /**
     * 发送签名验证码
     * @return
     */
    public Flowable<SignAuthCodeResponse> sendSms() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<SignAuthCodeResponse>>() {
                    @Override
                    public Publisher<SignAuthCodeResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().sendSms(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<SignAuthCodeResponse>ioToMain());
    }



    /**
     * 获取 开庭案件信息列表,
     * @return
     */
    public Flowable<SignCaseResponse<SessionCase>> getSessionList(final String status,final String pageSize,final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<SignCaseResponse<SessionCase>>>() {
                    @Override
                    public Publisher<SignCaseResponse<SessionCase>> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getSessionList(
                                token,
                                status,
                                pageSize,
                                currentPage
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<SignCaseResponse<SessionCase>>ioToMain());
    }

    /**
     * 获取通用消息列表界面信息
     *
     * @param length
     * @param currentPage
     * @return
     */
    public Flowable<NewsResponse> getMessList(final String url,final String length, final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<NewsResponse>>() {
                    @Override
                    public Publisher<NewsResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getMessList(
                                url,
                                token,
                                length,
                                currentPage
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<NewsResponse>ioToMain());
    }




    /**
     * 获取案件列表
     * @param paramMap
     * @return
     */
    public Flowable<CaseResponse> getCaseList(final Map<String,String> paramMap) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CaseResponse>>() {
                    @Override
                    public Publisher<CaseResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCaseList(
                                token,
                                paramMap
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CaseResponse>ioToMain());
    }

    /**
     * 获取案件详细信息
     * @param mainInfoCid 案件的唯一标识
     * @return
     */
    public Flowable<CaseDetailResponse> getCaseInfo(final String mainInfoCid) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CaseDetailResponse>>() {
                    @Override
                    public Publisher<CaseDetailResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCaseInfo(
                                token,
                                mainInfoCid
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CaseDetailResponse>ioToMain());
    }

    /**
     * 获取消息类型列表
     * @return
     */
    public Flowable<MessageTypeReponse> getMessageType() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<MessageTypeReponse>>() {
                    @Override
                    public Publisher<MessageTypeReponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getMessageTypeList(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<MessageTypeReponse>ioToMain());
    }
    /**
     * 获取取消组庭的消息详情
     * @return
     */
    public Flowable<CancleCaseResponse> getCancleCaseInfo(final String mainInfoCid) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CancleCaseResponse>>() {
                    @Override
                    public Publisher<CancleCaseResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCancleCaseInfo(
                                token,
                                mainInfoCid
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CancleCaseResponse>ioToMain());
    }

    /**
     * 消息标志为已读（点开消息阅读时调用
     * @return
     */
    public Flowable<ResponseBody> readMess(final String remindCid) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().readMess(
                                token,
                                remindCid
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }



    /**
     * 获取消息列表
     * @return
     */
    public Flowable<MessageResponse> getMessageList(final String messageType, final String length, final String currentPage) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<MessageResponse>>() {
                    @Override
                    public Publisher<MessageResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getMessageList(
                                token,
                                messageType,
                                length,
                                currentPage,
                                ""
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<MessageResponse>ioToMain());
    }



    /**
     * 分月统计案件数量
     * @return
     */
    public Flowable<CaseCountResponse> getCaseCount(final int caseYear, final String caseType) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CaseCountResponse>>() {
                    @Override
                    public Publisher<CaseCountResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCaseCount(
                                token,
                                String.valueOf(caseYear),
                                caseType
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CaseCountResponse>ioToMain());
    }

    
    /**
     * 获取饼图统计数据
     * @return
     */
    public Flowable<CaseStatsResponse> getCaseStatsForPie(final int caseYear) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CaseStatsResponse>>() {
                    @Override
                    public Publisher<CaseStatsResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCaseStatsForPie(
                                token,
                                String.valueOf(caseYear)
                        );
                    }
                })
                .map(new Function<CaseStatsResponse, CaseStatsResponse>() {
                    @Override
                    public CaseStatsResponse apply(CaseStatsResponse caseStatsResponse) throws Exception {

                        return caseStatsResponse;
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CaseStatsResponse>ioToMain());
    }

    /**
     * 获取仲裁员申请情况列表
     * @return
     */
    public Flowable<List<PersonMaterResponse>> getApplyList(final String commissionName) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<List<PersonMaterResponse>>>() {
                    @Override
                    public Publisher<List<PersonMaterResponse>> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getApplyList(
                                token,
                                String.valueOf(commissionName)
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<List<PersonMaterResponse>>ioToMain());
    }
    /**
     * 获取仲裁员签名审核信息资料
     * @return
     */
    public Flowable<AuditStatusResponse> getAuditStatus() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<AuditStatusResponse>>() {
                    @Override
                    public Publisher<AuditStatusResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getAuditStatus(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<AuditStatusResponse>ioToMain());
    }
    /**
     * 保存签名图片接口--base64
     * @return
     */
    public Flowable<ResponseBody> uploadSignPhoto(final String photoFile) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {

                        //通过img的file 转换成base64
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        Bitmap bitmap = BitmapFactory.decodeFile(photoFile);
                        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
                        byte[] imageBytes = baos.toByteArray();
                        String photoSignData = Base64.encodeToString(imageBytes,Base64.DEFAULT);
                        return  RetrofitManager.INSTANCE.getMainService().uploadSignPhoto(
                                token,
                                photoSignData
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                ;
    }
    /**
     * 保存APP头像
     * @return
     */
    public Flowable<ResponseBody> uploadAvatar(final String avatar) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        if (StringUtil.INSTANCE.isEmpty(avatar)) return null;

                        File imgFile = new File(avatar);

                        if (!imgFile.exists()) return null;

                        MediaType imageType = MediaType.parse("multipart/form-data");
                        RequestBody avatartBody = RequestBody.create(imageType, imgFile);

                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("headIconFile", imgFile.getName(), avatartBody);

                        return RetrofitManager.INSTANCE.getMainService().uploadAvatar(
                                token,
                                body
                        );
                    }
                })
                .retryWhen(new RetryLogin());
    }
    /**
     * 上传仲裁员资料头像
     * @return
     */
    public Flowable<ResponseBody> uploadArbAvatar(final String avatar) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        if (StringUtil.INSTANCE.isEmpty(avatar)) return null;

                        File imgFile = new File(avatar);

                        if (!imgFile.exists()) return null;

                        MediaType imageType = MediaType.parse("multipart/form-data");
                        RequestBody avatartBody = RequestBody.create(imageType, imgFile);

                        MultipartBody.Part body =
                                MultipartBody.Part.createFormData("headIconFile", imgFile.getName(), avatartBody);

                        return RetrofitManager.INSTANCE.getMainService().uploadArbAvatar(
                                token,
                                body
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                ;
    }






    /**
     * 获取申请机构仲裁员调查问卷--问题列表
     * @return
     */
    public Flowable<QuestionResponse> getQuestionList(final String commissionCid) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<QuestionResponse>>() {
                    @Override
                    public Publisher<QuestionResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getQuestionList(
                                token,
                                commissionCid
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<QuestionResponse>ioToMain());
    }

    /**
     * 获取仲裁员任职情况--答案信息
     * @return
     */
    public Flowable<AnswerResponse> getAnswerList(final String commissionCid) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<AnswerResponse>>() {
                    @Override
                    public Publisher<AnswerResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getAnswerList(
                                token,
                                commissionCid
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<AnswerResponse>ioToMain());
    }
    /**
     * 获取仲裁机构信息列表
     * @return
     */
    public Flowable<List<CommissionResponse>> getCommissionList() {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<List<CommissionResponse>>>() {
                    @Override
                    public Publisher<List<CommissionResponse>> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCommissionList(
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<List<CommissionResponse>>ioToMain());
    }


    /**
     * 提交申请（临时 / 机构）
     * @return
     */
    public Flowable<ResponseBody> submitApply(
            final String applyType,
            final String commissionCid,
            final String commissionName
    ) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {


                        String promise = "我自愿申请成为中国广州仲裁委员会仲裁员并成为广州仲裁员协会会员，遵守《中华人民共和国仲裁法》《中国广州仲裁委员会仲裁规则》等相关规定，拥护广州仲裁员协会章程，认真履行权利及义务，自觉维护中国广州仲裁委员会及广州仲裁员协会的合法权益，积极参与各项活动，按规定交纳会费。本人郑重承诺：自加入中国广州仲裁委员会后，即不再受聘担任广东省内其他仲裁机构仲裁员，如有违反，作自动退出中国广州仲裁委员会仲裁员处理。";

                        return  RetrofitManager.INSTANCE.getMainService().submitApply(
                                token,
                                applyType,
                                commissionCid,
                                commissionName,
                                "",
                                promise,
                                "APPSUBMIT"
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }

    /**
     *  获取公共参数
     *
     */
    public Flowable<CommonFileResponse> getCommonFiles(
            final String identity,
            final String fieldType
            ) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<CommonFileResponse>>() {
                    @Override
                    public Publisher<CommonFileResponse> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getCommonField(
                                token,
                                identity,
                                fieldType
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<CommonFileResponse>ioToMain());
    }


    /**
     *  获取附件文件列表
     *
     */
    public Flowable<List<String>> getFolder(
            final String path
            ) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<List<String>>>() {
                    @Override
                    public Publisher<List<String>> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().getFolder(
                                token,
                                path
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<List<String>>ioToMain());
    }
    /**
     *  撤销某项修改
     *
     */
    public Flowable<ResponseBody> cancelApply(
            final String arbitratorModifyAuditCid,
            final String modifyField
            ) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().cancelApply(
                                token,
                                arbitratorModifyAuditCid,
                                modifyField
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }

    /**
     *  撤销某项修改
     *
     */
    public Flowable<ResponseBody> delInfoList(
            final String delKey,
            final String delType
    ) {
        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        return  RetrofitManager.INSTANCE.getMainService().delInfoList(
                                token,
                                delKey,
                                delType
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }


    /**
     * 下载模板
     * @param url
     * @param commissionId
     * @return
     */
    public Flowable<ResponseBody> downloadTemplate(
            final String url,
            final String commissionId) {

        return Flowable.defer(RxUtil.getToken())
                .flatMap(new Function<String, Publisher<ResponseBody>>() {
                    @Override
                    public Publisher<ResponseBody> apply(String token) throws Exception {
                        String urlString = url.replace("{commissionId}",commissionId);
                        return  RetrofitManager.INSTANCE.getMainService().downloadTemplate(
                                urlString,
                                token
                        );
                    }
                })
                .retryWhen(new RetryLogin())
                .compose(SchedulerUtils.INSTANCE.<ResponseBody>ioToMain());
    }

}
