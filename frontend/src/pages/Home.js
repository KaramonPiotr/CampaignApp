import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import Account from "../account/Account.js"

export default function Home() {
    const {id} = useParams()
    const [campaigns, setCampaigns] = useState([]);
    useEffect(() => {
        loadCampaigns();
    }, []);
    const loadCampaigns = async () => {
        const result = await axios.get("http://localhost:8080/api/campaigns");
        console.log(result.data);
        setCampaigns(result.data);
    }
    const deleteCampaign= async (id) => {
        await axios.delete(`http://localhost:8080/api/campaigns/${id}`)
        loadCampaigns();
    }
    return (
        <div className="container">
            <Account></Account>
            <div className="py-4">
                <table className="table border shadow">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">CampaignName</th>
                            <th scope="col">Keywords</th>
                            <th scope="col">BidAmount</th>
                            <th scope="col">CampaignFund</th>
                            <th scope="col">Status</th>
                            <th scope="col">Town</th>
                            <th scope="col">Radius</th>
                            <th scope="col">Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        {
                            campaigns.map((campaign, index) => (
                                <tr>
                                    <th scope="row" key={index}>{index+1}</th>
                                    <td>{campaign.campaignName}</td>
                                    <td>{campaign.keywords}</td>
                                    <td>{campaign.bidAmount}</td>
                                    <td>{campaign.campaignFund}</td>
                                    <td>{campaign.status?"On":"Off"}</td>
                                    <td>{campaign.town}</td>
                                    <td>{campaign.radius}</td>
                                    <td>
                                        {/* <button className="btn btn-primary mx-2">View</button> */}
                                        <Link className="btn btn-outline-primary mx-2" to={`/editcampaign/${campaign.id}`}>Edit</Link>
                                        <button className="btn btn-danger mx-2" onClick={() => deleteCampaign(campaign.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))
                        }

                    </tbody>
                </table>
            </div>
        </div>
    )
}