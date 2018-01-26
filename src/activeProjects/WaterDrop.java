package activeProjects;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import databaseModel.Project;
import modules.ScheduleModule;
import parameter.ParameterType;
import parameter.SystemParameter;
import pukkaEnum.EnumElement;
import pukkaEnum.NamedIdEnum;
import pukkaEnum.PukkaEnumInterface;
import service.*;

/****************************************************************************
 *
 *          The Twigeo WaterDrop project
 *
 *
 */
public class WaterDrop extends Project {

    public static void main(String[] args){

        new WaterDrop();

    }
    private static final DataTable[] tables = {


            new DataTable("Outcome", "campaignData", "Extracted data for a campaign")

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "date"))
                    .withDataElement(new DataElement(DataType.INT, "ad"))
                    .withDataElement(new DataElement(DataType.INT, "breakdown_primary"))
                    .withDataElement(new DataElement(DataType.INT, "breakdown_secondary"))
                    .withDataElement(new DataElement(DataType.INT, "impressions"))
                    .withDataElement(new DataElement(DataType.INT, "clicks"))
                    .withDataElement(new DataElement(DataType.INT, "reach"))
                    .withDataElement(new DataElement(DataType.INT, "reach_to_date"))    // To date
                    .withDataElement(new DataElement(DataType.INT, "installs"))
                    .withDataElement(new DataElement(DataType.INT, "relevance"))
                    .withDataElement(new DataElement(DataType.INT, "comments"))
                    .withDataElement(new DataElement(DataType.INT, "likes"))
                    .withDataElement(new DataElement(DataType.INT, "spend"))
                    .withDataElement(new DataElement(DataType.INT, "revenue"))

                    .withDataElement(new DataElement(DataType.STRING, "platform"))
                    .withDataElement(new DataElement(DataType.STRING, "optimization_goal"))
                    .withDataElement(new DataElement(DataType.STRING, "custom_event_type"))

                    .withDataElement(new DataElement(DataType.INT, "achievement_unlocked"))
                    .withDataElement(new DataElement(DataType.INT, "activate"))
                    .withDataElement(new DataElement(DataType.INT, "payment_info"))
                    .withDataElement(new DataElement(DataType.INT, "add_to_cart"))
                    .withDataElement(new DataElement(DataType.INT, "add_to_wishlist"))
                    .withDataElement(new DataElement(DataType.INT, "complete_registration"))
                    .withDataElement(new DataElement(DataType.INT, "content_view"))
                    .withDataElement(new DataElement(DataType.INT, "initiated_checkout"))
                    .withDataElement(new DataElement(DataType.INT, "level_achieved"))
                    .withDataElement(new DataElement(DataType.INT, "purchase"))
                    .withDataElement(new DataElement(DataType.INT, "rate"))
                    .withDataElement(new DataElement(DataType.INT, "search"))
                    .withDataElement(new DataElement(DataType.INT, "spent_credits"))
                    .withDataElement(new DataElement(DataType.INT, "completed_tutorial"))
                    .withDataElement(new DataElement(DataType.INT, "other"))
                    .withDataElement(new DataElement(DataType.INT, "comment"))
                    .withDataElement(new DataElement(DataType.INT, "landing_page_view"))
                    .withDataElement(new DataElement(DataType.INT, "install"))
                    .withDataElement(new DataElement(DataType.INT, "like"))
                    .withDataElement(new DataElement(DataType.INT, "link_click"))
                    .withDataElement(new DataElement(DataType.INT, "leadad"))
                    .withDataElement(new DataElement(DataType.INT, "post"))
                    .withDataElement(new DataElement(DataType.INT, "post_reaction"))
                    .withDataElement(new DataElement(DataType.INT, "video_view"))
                    .withDataElement(new DataElement(DataType.INT, "page_engagement"))
                    .withDataElement(new DataElement(DataType.INT, "post_engagement"))
                    .withDataElement(new DataElement(DataType.INT, "conversion"))

                    .withDataElement(new DataElement(DataType.INT, "payment_info_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "add_to_cart_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "add_to_wishlist_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "complete_registration_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "initiated_checkout_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "initiated_purchase_pixel"))
                    .withDataElement(new DataElement(DataType.INT, "content_view_pixel"))

                    .withDataElement(new DataElement(DataType.INT, "custom"))


                    .withDataElement(new DataElement(DataType.INT, "custom2"))
                    .withDataElement(new DataElement(DataType.INT, "custom3"))
                    .withDataElement(new DataElement(DataType.INT, "custom4"))
                    .withDataElement(new DataElement(DataType.INT, "custom5"))
                    .withDataElement(new DataElement(DataType.INT, "custom6"))
                    .withDataElement(new DataElement(DataType.INT, "custom7"))
                    .withDataElement(new DataElement(DataType.INT, "custom8"))
                    .withDataElement(new DataElement(DataType.INT, "custom9"))
                    .withDataElement(new DataElement(DataType.INT, "custom10"))


            ,


            new DataTable("Breakdown", "campaignData", "The different supported breakdowns")

                    .withDataElement(new DataElement(DataType.TEXT, "label"))
                    .withDataElement(new DataElement(DataType.TEXT, "value"))
                            //.withDataElement(new DataElement(DataType.TEXT, "secondary_value"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Account", "campaignData", "The account")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_account_id").setIndex())
                    .withDataElement(new DataElement(DataType.BOOLEAN, "rw"))
                    .withDataElement(new DataElement(DataType.TEXT, "adminEmail"))
                    .withDataElement(new DataElement(DataType.STRING, "slackChannel"))
                    .withDataElement(new DataElement(DataType.STRING, "slackToken"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastUpdate"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastShift"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Campaign", "campaignData", "The different campaigns")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_campaign_id").setIndex())
                    .withDataElement(new DataElement(DataType.REFERENCE, "account", "account").setIndex())
                    .withDataElement(new DataElement(DataType.STRING, "objective").setIndex())
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("EventMapping", "campaignData", "Mapping of custom event types fro accounts")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.REFERENCE, "account", "account").setIndex())
                    .withDataElement(new DataElement(DataType.INT, "custom_column"))
                    .withDataElement(new DataElement(DataType.STRING, "event_id").setIndex())
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastUpdate")),


            new DataTable("Adset", "campaignData", "The different ad sets")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_adset_id").setIndex())
                    .withDataElement(new DataElement(DataType.BOOLEAN, "active"))
                    .withDataElement(new DataElement(DataType.REFERENCE, "campaign", "campaign"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Ad", "campaignData", "The different ads")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_ad_id").setIndex())
                    .withDataElement(new DataElement(DataType.STRING, "facebook_creative_id"))
                    .withDataElement(new DataElement(DataType.REFERENCE, "adset", "adset"))
                    .withDataElement(new DataElement(DataType.INT, "creative", "creative"))
                    .withDataElement(new DataElement(DataType.STRING, "creative_type"))
                    .withDataElement(new DataElement(DataType.INT, "saturation"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Creative", "campaignData", "The different creatives used in ads")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.TEXT, "image"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "headline"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "caption"))
                    .withDataElement(new DataElement(DataType.TEXT, "call_to_action"))
                    .withDataElement(new DataElement(DataType.TEXT, "creative_type"))
                    .withDataElement(new DataElement(DataType.TEXT, "facebook_creative_id")),

            new DataTable("Saturation", "campaignData", "Saturation calculated for ads")

                    .withDataElement(new DataElement(DataType.INT, "ad", "ad"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "created"))
                    .withDataElement(new DataElement(DataType.INT, "saturation")),


            new DataTable("StatResult", "campaignData", "Calculated stats")

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "generationTime"))
                    .withDataElement(new DataElement(DataType.TEXT, "filter"))
                    .withDataElement(new DataElement(DataType.TEXT, "adName"))
                    .withDataElement(new DataElement(DataType.TEXT, "adId"))
                    .withDataElement(new DataElement(DataType.TEXT, "adSet"))
                    .withDataElement(new DataElement(DataType.TEXT, "campaign"))
                    .withDataElement(new DataElement(DataType.TEXT, "breakdown"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "date"))
                    .withDataElement(new DataElement(DataType.INT, "spend"))
                    .withDataElement(new DataElement(DataType.INT, "impressions"))
                    .withDataElement(new DataElement(DataType.INT, "clicks"))
                    .withDataElement(new DataElement(DataType.INT, "saturation"))
                    .withDataElement(new DataElement(DataType.INT, "relevance"))
                    .withDataElement(new DataElement(DataType.INT, "score"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "explanation")),

            new DataTable("AnalysisResult", "campaignData", "Result of the analysis")

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "generationTime"))
                    .withDataElement(new DataElement(DataType.INT, "competition"))
                    .withDataElement(new DataElement(DataType.TEXT, "filter"))
                    .withDataElement(new DataElement(DataType.INT, "creative"))
                    .withDataElement(new DataElement(DataType.INT, "volume"))
                    .withDataElement(new DataElement(DataType.INT, "saturation"))
                    .withDataElement(new DataElement(DataType.INT, "score"))
                    .withDataElement(new DataElement(DataType.INT, "winDays"))
                    .withDataElement(new DataElement(DataType.INT, "winRatio"))
                    .withDataElement(new DataElement(DataType.STRING, "confidence"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "explanation"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "scoreSeries"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "positionSeries")),

            new DataTable("CompetitionResult", "campaignData", "Result of the analysis")

                    .withDataElement(new DataElement(DataType.STRING, "type"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "generationTime"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.INT, "depth"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "start"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "end"))
                    .withDataElement(new DataElement(DataType.TEXT, "filter"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "overview")),


            new DataTable("AdSetTypeRandomization", "facebook", "Analysis")

                    .withDataElement(new DataElement(DataType.INT, "creativeType"))
                    .withDataElement(new DataElement(DataType.INT, "activeType"))
                    .withDataElement(new DataElement(DataType.INT, "randomizedAdSet"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "updated"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "changed"))
                    .withDataElement(new DataElement(DataType.INT, "cycleId"))
                    .withDataElement(new DataElement(DataType.INT, "quarters"))
                    .withDataElement(new DataElement(DataType.INT, "participations")),

            new DataTable("RandomizedAdSet", "facebook", "Analysis")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.INT, "type"))
                    .withDataElement(new DataElement(DataType.TEXT, "AdSetId"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "active"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "created"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "reset"))
                    .withDataElement(new DataElement(DataType.INT, "reduceToParameter"))
                    .withDataElement(new DataElement(DataType.INT, "fairnessParameter"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "allTogetherParameter"))
            ,


    new DataTable("Randomization", "facebook", "Analysis")

                    .withDataElement(new DataElement(DataType.INT, "typeRandomization"))
                    .withDataElement(new DataElement(DataType.TEXT, "AdId"))
                    .withDataElement(new DataElement(DataType.TEXT, "adName"))
                    .withDataElement(new DataElement(DataType.TEXT, "creative"))
                    .withDataElement(new DataElement(DataType.INT, "rating"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "scoreExplanation"))
                    .withDataElement(new DataElement(DataType.INT, "exposure"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastUpdate"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "state"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "included")),

            /*

            new DataTable("RandomizationQueue", "randomization", "Randomization jobs scheduled")

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "next"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.TEXT, "facebookAdSetId"))
                    .withDataElement(new DataElement(DataType.TEXT, "adsetName"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "executed")),

            */
            new DataTable("SystemParameter", "system", "system parameters")

                    .withDataElement(new DataElement(DataType.STRING, "parameter"))
                    .withDataElement(new DataElement(DataType.STRING, "value")),

            new DataTable("ActionLog", "randomization", "Action Log log")

                    .withDataElement(new DataElement(DataType.STRING, "action"))
                    .withDataElement(new DataElement(DataType.INT, "type"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.INT, "adset"))
                    .withDataElement(new DataElement(DataType.INT, "task"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "timestamp"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "text"))
                    .withOrderByColumn("timestamp"),

            new DataTable("Dimension", "tagging", "All dimensions in the system")

                    .withDataElement(new DataElement(DataType.STRING, "name"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.TEXT, "pattern"))
                    .withDataElement(new DataElement(DataType.TEXT, "type"))
                    .withDataElement(new DataElement(DataType.TEXT, "description"))
            ,

            new DataTable("Tag", "tagging", "All tags in the system")

                    .withDataElement(new DataElement(DataType.STRING, "name"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.INT, "dimension").setIndex())
                    .withDataElement(new DataElement(DataType.INT, "ordinal"))
                    .withDataElement(new DataElement(DataType.TEXT, "pattern"))
                    .withDataElement(new DataElement(DataType.TEXT, "image"))
                    .withDataElement(new DataElement(DataType.STRING, "colour"))
                    .withDataElement(new DataElement(DataType.TEXT, "description"))
                    ,

            new DataTable("AdTagging", "tagging", "all taggings for all ads")

                    .withDataElement(new DataElement(DataType.INT, "ad").setIndex())
                    .withDataElement(new DataElement(DataType.INT, "tag"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.INT, "dimension").setIndex())
                    .withDataElement(new DataElement(DataType.BOOLEAN, "manual"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "timeStamp"))
                    .withOrderByColumn("timestamp"),

    };





    private static final ServiceInterface[] services = {

            new RestService("Campaigns", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "primaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "secondaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.BOOLEAN, "byDay"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("stats"),

            new RestService("AdSet", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "primaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "secondaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.BOOLEAN, "byDay"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("stats"),



            new RestService("Ads", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "primaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "secondaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.BOOLEAN, "byDay"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("stats"),

            new SingleResultService("Analysis", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                            //.withGetParameter(new MandatoryParameter(DataType.STRING, "breakdown"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("randomization"),

            new RestService("Admin", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "command"))
                    .withGetParameter(new OptionalParameter(DataType.STRING, "value"))

                    .withPostParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "command"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "value"))
                    .withInclude("campaignData")
                    .withInclude("scheduler")
                    .withInclude("facebook")
                    .withInclude("tagging")
                    .withInclude("randomization"),

    new RestService("Randomization", "services/waterDrop")
                    .withLoginValidation()
                    .withRWRequirement()
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.TEXT, "action"))
                    //.withPostParameter(new OptionalParameter(DataType.TEXT, "id"))
                    .withInclude("campaignData")
                    .withInclude("randomization")
                    .withInclude("scheduler")
                    .withInclude("stats")
                    .withInclude("breakdown")
                    .withInclude("facebook")
                    .withInclude("sequence"),

    new SingleResultService("Update", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withGetParameter(new OptionalParameter(DataType.BOOLEAN, "newCycle"))
                    .withInclude("campaignData")
                    .withInclude("randomization")
                    .withInclude("facebook")
            ,

            new RestService("Stat", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withLoginValidation()
            ,

            new RestService("RandomizedAdSet", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withLoginValidation()
                    .withInclude("facebook")
            ,

            new RestService("Result", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withLoginValidation()
            ,

            new RestService("Dimension", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "name"))
                    .withPostParameter(new MandatoryParameter(DataType.TEXT, "description"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "pattern"))
                    .withLoginValidation()
                    .withInclude("campaignData")
                    .withInclude("tagging")
            ,

            new RestService("Tag", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "name"))
                    .withPostParameter(new MandatoryParameter(DataType.TEXT, "description"))
                    .withPostParameter(new MandatoryParameter(DataType.INT, "dimension"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "pattern"))
                    .withLoginValidation()
                    .withInclude("campaignData")
                    .withInclude("tagging")
            ,

            new SingleActionService("TagUpdate", "services/waterDrop")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "action"))
                    .withInclude("facebook")
                    .withInclude("campaignData")
                    .withInclude("randomization")
                    .withInclude("tagging")
                    .withInclude("acs.userManagement")
            ,


            new RestService("Competition", "services/waterDrop")
                    .withLoginValidation()
                    .withComment("Competition service retreives all competitions")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withGetParameter(new OptionalParameter(DataType.INT, "dimensionFilter"))
                    .withInclude("campaignData")
                    .withInclude("tagging")
                    .withInclude("score")
            ,

            new RestService("CompetitionChart", "services/waterDrop")
                    .withLoginValidation()
                    .withComment("Competition chart service retrieves all data from a competition to draw a chart ")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withGetParameter(new OptionalParameter(DataType.INT, "dimensionFilter"))
                    .withInclude("campaignData")
                    .withInclude("tagging")
                    .withInclude("score")
            ,



            new RestService("Account", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new OptionalParameter(DataType.BOOLEAN, "all"))            // As admin user, you can get all accounts
                    .withPostParameter(new OptionalParameter(DataType.STRING, "name"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "facebookAccount"))
                    .withPostParameter(new OptionalParameter(DataType.BOOLEAN, "active"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "slackChannel"))
                    .withPostParameter(new OptionalParameter(DataType.STRING, "adminEmail"))
                    .withInclude("campaignData")
                    .withInclude("acs.userManagement")
                    .withInclude("facebook")
                    .withInclude("randomization")
                    .withInclude("tagging")
            ,
            new SingleActionService("AddUser", "services/waterDrop")
                    .withLoginValidation()
                    .withPostParameter(new MandatoryParameter(DataType.STRING, "email"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("acs.userManagement")
            ,

            new RestService("ActionLog", "services/waterDrop")
                    .withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.INT, "accountFilter"))
                    .withGetParameter(new OptionalParameter(DataType.INT, "adsetFilter"))
                    .withGetParameter(new OptionalParameter(DataType.INT, "taskFilter"))
                    .withGetParameter(new OptionalParameter(DataType.STRING, "typeFilter"))
                    .withGetParameter(new OptionalParameter(DataType.TIMESTAMP, "date"))
                    .withInclude("acs.userManagement")
                    .withInclude("campaignData")
                    .withInclude("randomization")
                    .withInclude("facebook")
                    .withInclude("html"),

            new RestService("RandomizationQueue", "services/waterDrop")
                    .withLoginValidation()
                    .withInclude("randomization")
                    .withInclude("campaignData")




    };

    private static final SystemParameter[] parameters = {

            new SystemParameter("dbPwd", ParameterType.String, "noPassword"),
            new SystemParameter("dbUser", ParameterType.String, "noPassword"),
            new SystemParameter("dbConnection", ParameterType.String, "jdbc:mysql://localhost:3306/no DB Set"),
            new SystemParameter("APPLICATION", ParameterType.String, "no application"),
            new SystemParameter("APP_SECRET", ParameterType.String, "bbf92defba5905117c98ccf36efd4e3e"),
            new SystemParameter("ACCESS_TOKEN", ParameterType.String, "EAAHZBBrbVMpEBAL9f6uveSRhCf1JG1gn1Q2k69wZAtCoaBI9wtlQbESs5XOR3bsdqO2YMrdDZBUtZCZBBjE6kDzB45IpZATunKIryHj0jh4nUoUi6kLQT7jwx3OmrzjxlUeO0r7NHZCCCid3891R13yNGwbPrZBZADnEZD"),


            //"smtp.gmail.com", 587, "linusdalin@gmail.com", "16Twatwaommwl!" );
            new SystemParameter("EMAIL_HOST",       ParameterType.String, "smtp.gmail.com"),
            new SystemParameter("EMAIL_PORT",       ParameterType.String,  "587"),
            new SystemParameter("EMAIL_ACCOUNT",    ParameterType.String, "linus.dalin@gmail.com"),
            new SystemParameter("EMAIL_PASSWORD",   ParameterType.String, "no password"),





    };

    private static final PukkaEnumInterface[] enums = {

            new NamedIdEnum("TypeMode", "randomization")
                    .withDescription("Active running mode for a type randomization")
                    .withDataElement(new EnumElement("RANDOMIZE",   2, "Randomize"))
                    .withDataElement(new EnumElement("REDUCE",      5, "Reduce"))
                    .withDataElement(new EnumElement("ROTATE",      3, "Rotate")),

            new NamedIdEnum("AdSetStrategy", "randomization")
                    .withDescription("Selected strategies for the randomizations (tags on the adset in the facebook account)")
                    .withDataElement(new EnumElement("OPTIMIZE",    1, "#Optimize"))
                    .withDataElement(new EnumElement("TEST",        4, "#Test")),

            new NamedIdEnum("AdDirective", "randomization")
                    .withDescription("Directives on specific ads (tags on the ad in the facebook account)")
                    .withDataElement(new EnumElement("EXCLUDE",    11, "#Exclude"))
                    .withDataElement(new EnumElement("ELIMINATED", 12, "#Eliminated")),

            new NamedIdEnum("GenderBreakdown", "breakdown")
                    .withDescription("Breakdown values for the gender breakdown in competitions and stats ")
                    .withDataElement(new EnumElement("UNKNOWN", 1, "Unknown"))
                    .withDataElement(new EnumElement("MALE",    2, "Male"))
                    .withDataElement(new EnumElement("FEMALE",  3, "Female"))


    };



    public WaterDrop(){

        super("WaterDrop", "C:\\Users\\Linus\\Documents\\GitHub\\Twigeo\\WaterDrop\\src", "commons");

        withDatabase("C:\\Users\\Linus\\Documents\\GitHub\\Twigeo\\WaterDrop\\scripts", "water_drop");
        withServiceLayer("C:\\Users\\Linus\\Documents\\GitHub\\Twigeo\\WaterDropService\\src");
        withTestDirectory("test");
        withTables(tables);
        withServices(services);
        withParameters(parameters);
        withEnums(enums);

        // Adding standard modules
        withModule(new ScheduleModule());
        //withModule(new UserModule( ));

        // Generate the actual files
        generate();

    }


}
