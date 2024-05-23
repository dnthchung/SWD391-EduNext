/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.offer;

import dao.OfferDAO;
import dto.OfferRemindDTO;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import utils.EmailSender;

/**
 *
 * @author tranh
 */
public class OfferReminder implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        timer = new Timer(true);

        // Lấy thời điểm hiện tại
        Calendar currentTime = Calendar.getInstance();

        // Tính thời gian cần đợi cho đến 8 giờ sáng hôm sau
        Calendar scheduledTime = Calendar.getInstance();
        scheduledTime.set(Calendar.HOUR_OF_DAY, 8);
        scheduledTime.set(Calendar.MINUTE, 0);
        scheduledTime.set(Calendar.SECOND, 0);
        scheduledTime.set(Calendar.MILLISECOND, 0);
        if (scheduledTime.before(currentTime) || scheduledTime.equals(currentTime)) {
            scheduledTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        long delay = scheduledTime.getTimeInMillis() - currentTime.getTimeInMillis();

        // Lên lịch công việc để chạy vào 8 giờ sáng hàng ngày
        timer.scheduleAtFixedRate(new SendReminderTask(), delay, 24 * 60 * 60 * 1000);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        timer.cancel();
    }

    private class SendReminderTask extends TimerTask {

        @Override
        public void run() {
            OfferDAO offerDAO = new OfferDAO();
            List<OfferRemindDTO> offers = offerDAO.getOfferWithDueDateToday();
            for (OfferRemindDTO offer : offers) {
                String content = "<p>This is email from IMS system,</p><br>\n"
                        + "<p>You have an offer to take action For Candidate " + offer.getCandidateName() + " position " + offer.getCandidatePosition() + " before " + offer.getDueDate() + ", the contract is attached with this no-reply-email.</p>\n"
                        + "<p>Please refer this link to take action <a href=\"http://localhost:9999/ISM/offer-details?offerId=" + offer.getOfferId() + "\">http://localhost:9999/ISM/offer-details?offerId=" + offer.getOfferId() + "</a></p>\n"
                        + "<p>If anything wrong, please reach-out recruiter " + offer.getRecruiterAccount() + ". We are so sorry for this inconvenience.</p>\n"
                        + "<p>Thanks &amp; Regards!</p>\n"
                        + "<p>IMS Team.</p>";
                EmailSender.sendContentToEmailWithAttachment(offer.getApproverMail(),
                        "no-reply-email-IMS-system <Take action on Job offer>",
                        content,
                        offer.getCvAttachment(),
                        "CV - " + offer.getCandidateName());
                System.out.println("Sent offer reminder to " + offer.getApproverMail());
            }
        }
    }
}
