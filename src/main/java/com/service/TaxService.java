package com.service;

import com.domain.TaxRate;
import com.domain.Variables;
import com.repository.TaxCalcuRepository;
import com.repository.TaxRateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Service
@Transactional
public class TaxService {
    private TaxRateRepository taxRateRepository;
    private TaxCalcuRepository taxCalcuRepository;

    private DataSource dataSource;
    public TaxService(TaxCalcuRepository taxCalcuRepository,TaxRateRepository taxRateRepository) {
        this.taxCalcuRepository=taxCalcuRepository;
        this.taxRateRepository = taxRateRepository;
    }
    public double taxible_salary(int basic_salary,int conveyance,int medical,int house_rent,int festibal)
    {
        double taxble_inome=0;
        taxble_inome=taxble_inome+basic_salary;
        //convenyece
        if(conveyance>30000)
        {
            taxble_inome=taxble_inome+(conveyance-30000);}


        //house rent
        if(300000>basic_salary*0.5) {
            taxble_inome = taxble_inome +( house_rent - (basic_salary * 0.5));
        }
        else
        {
            taxble_inome = taxble_inome +Math.abs( 300000-house_rent);
        }
        System.out.println(taxble_inome);
        //medical
        if(basic_salary*0.1<120000)
        {
            taxble_inome=taxble_inome+medical-(basic_salary*0.1);
        }
        else
        {
            taxble_inome=taxble_inome+(medical-120000);
        }
        taxble_inome=taxble_inome+festibal;
        return taxble_inome;



    }
    @Transactional
    public Variables calcu(Variables variables)
    {
        String categorys=null;
        String zones=null;
        double ti=0;
        TaxRate taxRate= taxRateRepository.get();
        double tax=0;
        if(variables.getCategory()=='g') {
            double tax_money = taxible_salary(Integer.parseInt(variables.getBasic_salary()) * 12, Integer.parseInt(variables.getCon()) * 12, Integer.parseInt(variables.getMedical()) * 12, Integer.parseInt(variables.getHouse_rent()) * 12, Integer.parseInt(variables.getFestival()) * 2);
            ti=tax_money;

            tax_money=tax_money-taxRate.getGenaral1stSlab();
            System.out.println(taxRate.getGenaral1stSlab());

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getFirstSlab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFirstSlab()*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
            }

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getSslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getSslab()*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
            }


            if(tax_money>0)
            {double temp=tax_money-taxRate.getTslab();

                if(temp>=0)
                {
                    tax=tax+(taxRate.getTslab()*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
            }
            if(tax_money>0)
            {double temp=tax_money-taxRate.getFslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFslab()*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
            }
            if(tax_money>0)
            {
                tax=tax+(tax_money*taxRate.getReSlabRate());


            }

            tax=tax-(Integer.parseInt(variables.getInvestment())*0.15);

        } else if (variables.getCategory()=='f') {
            double tax_money = taxible_salary(Integer.parseInt(variables.getBasic_salary()) * 12, Integer.parseInt(variables.getCon()) * 12, Integer.parseInt(variables.getMedical()) * 12, Integer.parseInt(variables.getHouse_rent()) * 12, Integer.parseInt(variables.getFestival()) * 2);
            ti=tax_money;
            tax_money=tax_money-taxRate.getFemalgenaral1stSlab();

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getFirstSlab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFirstSlab()*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
            }

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getSslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getSslab()*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
            }


            if(tax_money>0)
            {double temp=tax_money-taxRate.getTslab();

                if(temp>=0)
                {
                    tax=tax+(taxRate.getTslab()*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
            }
            if(tax_money>0)
            {double temp=tax_money-taxRate.getFslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFslab()*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
            }
            if(tax_money>0)
            {
                tax=tax+(tax_money*taxRate.getReSlabRate());


            }

            tax=tax-(Integer.parseInt(variables.getInvestment())*0.15);

        }
        else if (variables.getCategory()=='d') {
            double tax_money = taxible_salary(Integer.parseInt(variables.getBasic_salary()) * 12, Integer.parseInt(variables.getCon()) * 12, Integer.parseInt(variables.getMedical()) * 12, Integer.parseInt(variables.getHouse_rent()) * 12, Integer.parseInt(variables.getFestival()) * 2);
            ti=tax_money;
            tax_money=tax_money-taxRate.getDisa1stSlab();

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getFirstSlab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFirstSlab()*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
            }

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getSslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getSslab()*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
            }


            if(tax_money>0)
            {double temp=tax_money-taxRate.getTslab();

                if(temp>=0)
                {
                    tax=tax+(taxRate.getTslab()*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
            }
            if(tax_money>0)
            {double temp=tax_money-taxRate.getFslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFslab()*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
            }
            if(tax_money>0)
            {
                tax=tax+(tax_money*taxRate.getReSlabRate());


            }

            tax=tax-(Integer.parseInt(variables.getInvestment())*0.15);

        }
        else if (variables.getCategory()=='e') {
            double tax_money = taxible_salary(Integer.parseInt(variables.getBasic_salary()) * 12, Integer.parseInt(variables.getCon()) * 12, Integer.parseInt(variables.getMedical()) * 12, Integer.parseInt(variables.getHouse_rent()) * 12, Integer.parseInt(variables.getFestival()) * 2);
            ti=tax_money;
            tax_money=tax_money-taxRate.getFf1stSlab();

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getFirstSlab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFirstSlab()*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getFirstSlabRate());
                    tax_money=tax_money-taxRate.getFirstSlab();
                }
            }

            if(tax_money>0)
            {
                double temp=tax_money-taxRate.getSslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getSslab()*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getSecSLabRate());
                    tax_money=tax_money-taxRate.getSslab();
                }
            }


            if(tax_money>0)
            {double temp=tax_money-taxRate.getTslab();

                if(temp>=0)
                {
                    tax=tax+(taxRate.getTslab()*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getThSlabRate());
                    tax_money=tax_money-taxRate.getTslab();
                }
            }
            if(tax_money>0)
            {double temp=tax_money-taxRate.getFslab();
                if(temp>=0)
                {
                    tax=tax+(taxRate.getFslab()*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
                else
                {
                    tax=tax+(tax_money*taxRate.getfSlabRate());
                    tax_money=tax_money-taxRate.getFslab();
                }
            }
            if(tax_money>0)
            {
                tax=tax+(tax_money*taxRate.getReSlabRate());


            }

            tax=tax-(Integer.parseInt(variables.getInvestment())*0.15);
        }

        if(variables.getCategory()=='g')
        {
            categorys="Genaral";
        } else if (variables.getCategory()=='f')
        {
            categorys="Female/Senior Citizen";
        } else if (variables.getCategory()=='d')
        {
            categorys="Disabled";
        }
        else if (variables.getCategory()=='e')
        {
            categorys="Freedom fighter";
        }

        if(variables.getZone()=='d')
        {
            zones="Dhaka/Chitagong";
        }
        else if(variables.getZone()=='c')
        {
            zones="Other City";
        }
        else if(variables.getZone()=='w')
        {
            zones="Rest of the world";
        }

        double m_tax=tax/12;
        variables.setTaxble_income(String.valueOf(ti));
        variables.setTotal_tax(String.valueOf(tax));
        variables.setM_tax(String.valueOf(m_tax));
        //sql
        taxCalcuRepository.create(variables);
        return variables;
    }
    @Transactional(readOnly = true)

    public List<Variables> gethistory(long userid) {
        return taxCalcuRepository.gethistory(userid);
    }
    public Variables update(Variables variables) {
        return taxCalcuRepository.update(variables);
    }

    @Transactional(readOnly = true)
    public Variables get(Long id) {
        return taxCalcuRepository.get(id);
    }
    @Transactional
    public void delete(Long id) {
        taxCalcuRepository.delete(id);
    }
    @Transactional(readOnly = true)
    public List<Variables> getAll() {
        return taxCalcuRepository.getAll();
    }
    @Transactional(readOnly = true)
    public List<Variables> get() {
        return taxCalcuRepository.getAll();
    }
    public TaxRate gettaxrate() {
        return taxRateRepository.get();
    }
    @Transactional
    public TaxRate updatet(TaxRate taxRate) {
        return taxRateRepository.update(taxRate);
    }

}
