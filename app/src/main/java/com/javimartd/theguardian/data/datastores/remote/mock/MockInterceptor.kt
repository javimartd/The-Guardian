package com.javimartd.theguardian.data.datastores.remote.mock

import com.javimartd.theguardian.BuildConfig
import okhttp3.*

class MockInterceptor: Interceptor {

    private val getListOfNewsJson = """
{
    "response":{
        "status":"ok",
        "startIndex":1,
        "pageSize":10,
        "currentPage":1,
        "orderBy":"newest",
        "results":[
            {
                "id":"stage/2020/oct/11/nights-in-the-gardens-of-spain-miss-fozzard-finds-her-feet-review-alan-bennett-bridge-theatre-london",
                "type":"article",
                "sectionId":"stage",
                "sectionName":"Stage",
                "webPublicationDate":"2020-10-11T09:30:02Z",
                "webTitle":"Nights in the Gardens of Spain & Miss Fozzard Finds Her Feet review – fetish with flair",
                "webUrl":"https://www.theguardian.com/stage/2020/oct/11/nights-in-the-gardens-of-spain-miss-fozzard-finds-her-feet-review-alan-bennett-bridge-theatre-london",
                "apiUrl":"https://content.guardianapis.com/stage/2020/oct/11/nights-in-the-gardens-of-spain-miss-fozzard-finds-her-feet-review-alan-bennett-bridge-theatre-london",
                "fields":{
                    "thumbnail":"https://media.guim.co.uk/265930ed89710083f8672b5410e649db0411e56f/507_64_4068_2442/500.jpg",
                    "isLive":"true",
                    "bodyText":"What a cunning double bill this is. The latest – um – coupling of Alan Bennett’s Talking Heads hinges on an activity long regarded as a perversion: middle-aged sex. Both these monologues feature fetishism: as cruelty and as ridiculous pleasure. Both offer remarkable actors the chance to extend their range. I have never seen Tamsin Greig look so fuzzy, so fractured with bewilderment, as she does in Nights in the Gardens of Spain. Married to a controlling man, poleaxed by politeness, she is suddenly confronted by darkness next door – hoods, handcuffs, leering fellows, a gunning down – and sees her own life in a new light. Greig bats her way through the air like a woman playing ping pong with a hurricane. She speaks in fluttering phrases; a smile falls on her face like a shadow. Marianne Elliott’s production delivers surprises with perfect pacing – the sinister face of silence, the desperation of the long-married woman: “I’m pinning my hopes on his prostate.” Finally, most truly unexpected of all, the discovery of a startling affection – which transfuses a bleak scene with tender feeling. I have also never seen Maxine Peake be funny on stage before. Yet in the title role of (oh, the name!) Miss Fozzard Finds Her Feet she cuts a tremendous comic caper – touches of Julie Walters, shades of pert 50s secretaries – before shifting register into something both more calculating and more warm. The audience starts giggling early in Sarah Frankcom’s finely focused production. Bunny Christie’s design plants a clue to what is coming, with pairs of shoes – stilettoes and some more comfy items – stationed on the stage. Miss F expresses a keen interest in her chiropodist, who is proud to have “your feet in my hands”: she is later asked by him to put her bootees to unusual use. The laughs don’t stop but the idea that the pair is merely ludicrous is put to shame. Is Bennett punning on “bootees”? Hard to know. At Bridge theatre, London, until 24 October"
                }
            },
            {
                "id":"food/2020/oct/12/thomasina-miers-recipe-tequila-spiked-chocolate-pots",
                "type":"article",
                "sectionId":"food",
                "sectionName":"Food",
                "webPublicationDate":"2020-10-12T11:00:25Z",
                "webTitle":"Thomasina Miers' recipe for tequila-spiked chocolate pots | The simple fix",
                "webUrl":"https://www.theguardian.com/food/2020/oct/12/thomasina-miers-recipe-tequila-spiked-chocolate-pots",
                "apiUrl":"https://content.guardianapis.com/food/2020/oct/12/thomasina-miers-recipe-tequila-spiked-chocolate-pots",
                "fields":{
                    "thumbnail":"https://media.guim.co.uk/21835a7c454d42f84255fc9a8a4ae5e5cffaa0e0/0_3646_5691_3414/500.jpg",
                    "isLive":"true",
                    "bodyText":"My chocolate consumption reached new heights over lock down, and I know I was not alone. Those little chocolate pots in the shops became a rather too frequent pleasure, even though they’re often expensive, a bit sweet and, annoyingly, contain palm oil, which I am trying to avoid. A homemade version is very simple, however: something suitably naughty, but comforting, and with a little Mexican twist. But be warned: they are totally irresistible. Tequila-spiked chocolate pots The combination of unaged tequila, dark chocolate and cream is unbelievably delicious, making this a true desert island treat. Prep 20 min Cook 1 hr Chill 3 hr Makes 6-8 6 egg yolks 3 tbsp caster sugar 1 pinch salt 150g 70% cocoa dark chocolate 250ml double cream 250ml whole milk ½ tsp ground cinnamon 1 tbsp 100% agave tequila, or rum (optional) Heat the oven to 170C (150C fan)/325F/gas 3. In a large bowl, beat the egg yolks, sugar and a pinch of salt with an electric mixer or whisk for two to three minutes, until pale. Meanwhile, melt the chocolate in a microwave or over a pan of just-simmering water. Put the cream and milk in a saucepan, bring to simmering point, then take off the heat and slowly whisk into the melted chocolate. Whisk this in turn into the beaten eggs, then whisk in the cinnamon and tequila, if using, and pour into a large jug. Divide the mixture between six to eight ramekins, cover each one with foil and place in a deep roasting pan. Bring a kettle to a boil, then pour enough hot water into the tray to come halfway up the sides of the ramekins. Bake for an hour, then lift out the ramekins from their hot bath, remove their foil lids and leave to cool. Once cool, refrigerate for three hours until thoroughly chilled. Serve with good pouring cream – you can find some delicious ones online and in farm shops – and little biscuits, if you like. And for the rest of the week … Make financiers with the leftover egg whites, and top with the very last of the season’s blackberries."
                }
            },
            {
                "id":"music/ng-interactive/2020/oct/07/the-months-best-album-reviews",
                "type":"interactive",
                "sectionId":"music",
                "sectionName":"Music",
                "webPublicationDate":"2020-10-12T11:45:13Z",
                "webTitle":"The month's best album reviews",
                "webUrl":"https://www.theguardian.com/music/ng-interactive/2020/oct/07/the-months-best-album-reviews",
                "apiUrl":"https://content.guardianapis.com/music/ng-interactive/2020/oct/07/the-months-best-album-reviews",
                "fields":{
                    "thumbnail":"https://media.guim.co.uk/3f890297edc424ffd259be580610c347cc2085e5/0_0_5000_3000/500.jpg",
                    "isLive":"true",
                    "bodyText":""
                }
            },
            {
                "id":"books/2020/oct/12/the-contradictions-by-sophie-yanow-review-on-the-road-with-a-raging-bore",
                "type":"article",
                "sectionId":"books",
                "sectionName":"Books",
                "webPublicationDate":"2020-10-12T08:00:29Z",
                "webTitle":"The Contradictions by Sophie Yanow review – on the road with a raging bore",
                "webUrl":"https://www.theguardian.com/books/2020/oct/12/the-contradictions-by-sophie-yanow-review-on-the-road-with-a-raging-bore",
                "apiUrl":"https://content.guardianapis.com/books/2020/oct/12/the-contradictions-by-sophie-yanow-review-on-the-road-with-a-raging-bore",
                "fields":{
                    "thumbnail":"https://media.guim.co.uk/ed5bf49c3fee1528375f8a41500f3b607138f0b9/22_1142_1488_893/500.jpg",
                    "isLive":"true",
                    "bodyText":"This comic, the original web version of which has already received an Eisner award, arrives with high praise both from Alison Bechdel, the bestselling author of Fun Home (“surprisingly, even transcendently, emotional”), and from Tillie Walden, best known for her acclaimed memoir Spinning (“makes you want to laugh and cry simultaneously”). It’s not hard to see why. Let us ignore, for a moment, the pandemic; the fact that nothing about university life is quite as it was, or should be, right now. This funny and very knowing graphic novel will still strike an exceedingly loud chord with anyone who is, or has ever been, a fresher, far from home and all at sea. Open it and you can almost smell the Nescafé. Booksellers should throw in a free jar of the stuff with every copy sold. The confused and somewhat apathetic heroine of The Contradictions is Sophie (possibly a version of the book’s author, Sophie Yanow), an American studying in Paris. Broke and lonely, she thinks her prayers may have been answered when she meets her compatriot Zena, the proud rider of a fixed gear bicycle (Sophie loves bikes, though she has left her own back at home). But beware those first-term friends! Her new pal turns out to be a moody, shoplifting, vegan anarchist, and when Zena suggests that the two of them spend a few days together hitchhiking to Berlin via Amsterdam, the reader knows the trip can’t possibly end well. It would be hard enough to tolerate Zena’s particular brand of self-righteousness in a luxury hotel room, but to have to listen to her anti-capitalist mutterings in a tiny tent pitched by a motorway service station surely cannot result in anything less than murderous thoughts and a powerful longing for a cheeseburger. Sophie, though, is a little too docile for murderous thoughts – and in any case, Zena’s half-baked ideas and startling hypocrisies are ultimately not half so exasperating as her complete lack of interest in her surroundings. “Paris is a cheap whore,” she tells one of the drivers who pick them up. “She’s for sale to the highest bidder.” And so it goes on. No museum nor gallery, no matter how great, is of any interest at all to Zena; European food, with its dependence on meat, butter and cheese, is in her eyes simply disgusting. All poor Sophie can do is follow her around, feeling hungry, nuts being just about the only thing she’s permitted to eat in Zena’s presence. Drawn in black and white, Yanow’s figures are a couple of rectangles topped by the circles of their anoraks, rucksacks and spectacles, while the boulevards and canals around them appear hardly at all. But though such a pared-back style can hardly be said to be beautiful, it’s perfect here. As they trudge from city to city, their days bereft of beauty, variety and everyday joy, Sophie and Zena could be almost anywhere. In the end, for all its comedy, The Contradictions is a book about how principles, if too firmly held, can make a person blind – not just to new ideas, but to all the good things in the world. • The Contradictions by Sophie Yanow is published by Drawn & Quarterly (£19.99). To order a copy go to guardianbookshop.com. Delivery charges may apply"
                }
            }
        ]
    }
}

"""

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.contains("search") -> getListOfNewsJson else -> ""
            }
            return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(ResponseBody.create(MediaType.parse("application/json"),
                            responseString.toByteArray()))
                    .addHeader("content-type", "application/json")
                    .build()
        } else {
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }
}