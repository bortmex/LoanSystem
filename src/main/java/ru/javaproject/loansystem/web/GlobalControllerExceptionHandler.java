package ru.javaproject.loansystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.javaproject.loansystem.AuthorizedUser;
import ru.javaproject.loansystem.util.ValidationUtil;
import ru.javaproject.loansystem.util.exception.ApplicationException;
import ru.javaproject.loansystem.util.exception.ErrorType;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) throws Exception {
        return getView(req, appEx, appEx.getType(), messageUtil.getMessage(appEx));
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("exception", e);

        // Interceptor is not invoked, put userTo
        AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
        if (authorizedUser != null) {
            mav.addObject("userTo", authorizedUser.getUserTo());
        }
        return mav;
    }

    public ModelAndView getView(HttpServletRequest req, Exception e, ErrorType type, String msg) throws Exception {
        Throwable rootCause = ValidationUtil.getRootCause(e);
        log.error("Exception at request " + req.getRequestURL(), rootCause);
        ModelAndView mav = new ModelAndView("exception/exception");
        mav.addObject("typeMessage", messageUtil.getMessage(type.getErrorCode()));
        mav.addObject("exception", rootCause);
        mav.addObject("message", msg != null ? msg : rootCause.toString());
        return mav;
    }
}
