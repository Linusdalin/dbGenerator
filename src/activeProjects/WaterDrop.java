package activeProjects;

import databaseModel.DataElement;
import databaseModel.DataTable;
import databaseModel.DataType;
import databaseModel.Project;
import modules.ScheduleModule;
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
                    .withDataElement(new DataElement(DataType.INT, "revenue")),

            new DataTable("Breakdown", "campaignData", "The different supported breakdowns")

                    .withDataElement(new DataElement(DataType.TEXT, "label"))
                    .withDataElement(new DataElement(DataType.TEXT, "value"))
                            //.withDataElement(new DataElement(DataType.TEXT, "secondary_value"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Account", "campaignData", "The account")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_account_id").setIndex())
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Campaign", "campaignData", "The different campaigns")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_campaign_id").setIndex())
                    .withDataElement(new DataElement(DataType.REFERENCE, "account", "account").setIndex())
                    .withDataElement(new DataElement(DataType.STRING, "objective").setIndex())
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Adset", "campaignData", "The different ad sets")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_adset_id").setIndex())
                    .withDataElement(new DataElement(DataType.REFERENCE, "campaign", "campaign"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Ad", "campaignData", "The different ads")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.STRING, "facebook_ad_id").setIndex())
                    .withDataElement(new DataElement(DataType.REFERENCE, "adset", "adset"))
                    .withDataElement(new DataElement(DataType.INT, "creative", "creative"))
                    .withDataElement(new DataElement(DataType.STRING, "creative_type"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "description")),

            new DataTable("Creative", "campaignData", "The different creatives used in ads")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.TEXT, "image"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "headline"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "caption"))
                    .withDataElement(new DataElement(DataType.TEXT, "call_to_action"))
                    .withDataElement(new DataElement(DataType.TEXT, "creative_type"))
                    .withDataElement(new DataElement(DataType.TEXT, "facebook_creative_id")),

    /*
   ALTER table creative ADD COLUMN call_to_action varchar(45) DEFAULT NULL;
ALTER table creative ADD COLUMN creative_type varchar(45) DEFAULT NULL;
ALTER table creative ADD COLUMN facebook_creative_id varchar(40) DEFAULT NULL;
    */

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

                    .withDataElement(new DataElement(DataType.TIMESTAMP, "generationTime"))
                    .withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "start"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "end"))
                    .withDataElement(new DataElement(DataType.TEXT, "filter"))
                    .withDataElement(new DataElement(DataType.LONG_TEXT, "overview")),


            new DataTable("RandomizedAdSet", "facebook", "Analysis")

                    .withDataElement(new DataElement(DataType.TEXT, "name"))
                    .withDataElement(new DataElement(DataType.TEXT, "AdSetId"))
                    .withDataElement(new DataElement(DataType.TEXT, "campaign"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "active"))
                    .withDataElement(new DataElement(DataType.INT, "cycleId"))
                    .withDataElement(new DataElement(DataType.INT, "participations")),

    new DataTable("Randomization", "facebook", "Analysis")

                    //.withDataElement(new DataElement(DataType.INT, "account"))
                    .withDataElement(new DataElement(DataType.INT, "adSet"))
                    .withDataElement(new DataElement(DataType.TEXT, "AdId"))
                    .withDataElement(new DataElement(DataType.TEXT, "adName"))
                    .withDataElement(new DataElement(DataType.INT, "rating"))
                    .withDataElement(new DataElement(DataType.TIMESTAMP, "lastUpdate"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "state"))
                    .withDataElement(new DataElement(DataType.BOOLEAN, "included")),

            new DataTable("ActiveAccount", "accountManagement", "Active account for a user")

                    .withDataElement(new DataElement(DataType.INT, "user"))
                    .withDataElement(new DataElement(DataType.INT, "account"))

    };

    private static final ServiceInterface[] services = {

            new PukkaService("Campaigns", "services/waterDrop")
                    //.withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "primaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "secondaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.BOOLEAN, "byDay"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("stats"),

            new PukkaService("AdSet", "services/waterDrop")
                    //.withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "primaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "secondaryBreakdown"))
                    .withGetParameter(new MandatoryParameter(DataType.BOOLEAN, "byDay"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("stats"),



            new PukkaService("Ads", "services/waterDrop")
                    //.withLoginValidation()
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
                    //.withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "period"))
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                            //.withGetParameter(new MandatoryParameter(DataType.STRING, "breakdown"))
                    .withInclude("campaignData")
                    .withInclude("breakdown")
                    .withInclude("score")
                    .withInclude("testRandomization"),

            new PukkaService("Admin", "services/waterDrop")
                    //.withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.STRING, "command"))
                    .withGetParameter(new OptionalParameter(DataType.STRING, "value"))
                    .withInclude("campaignData"),

            new PukkaService("Randomization", "services/waterDrop")
                    //.withLoginValidation()
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
                    .withInclude("testRandomization")
                    .withInclude("facebook"),

            new PukkaService("Stat", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
            //.withLoginValidation()
            ,

            new PukkaService("Result", "services/waterDrop")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
            //.withLoginValidation()
            ,

            new PukkaService("Competition", "services/waterDrop")
            //.withLoginValidation()
                    .withComment("Competition service retreives all competitions")
                    .withGetParameter(new MandatoryParameter(DataType.INT, "account"))
                    .withInclude("campaignData")
            ,

            new PukkaService("Account", "services/waterDrop")
                    //.withLoginValidation()
                    .withInclude("campaignData")
            ,


    };


    public WaterDrop(){

        super("waterdrop", "C:\\Users\\Linus\\Documents\\GitHub\\WaterDrop\\src", "commons");

        withDatabase("C:\\Users\\Linus\\Documents\\GitHub\\WaterDrop\\scripts", "water_drop");
        withServiceLayer("C:\\Users\\Linus\\Documents\\GitHub\\WaterDropService\\src");
        withTestDirectory("test");
        withTables(tables);
        withServices(services);
        withModule(new ScheduleModule());
        // Generate the actual files
        generate();

    }


}
